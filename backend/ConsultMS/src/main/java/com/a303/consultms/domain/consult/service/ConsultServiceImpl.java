package com.a303.consultms.domain.consult.service;

import static com.a303.consultms.global.exception.code.ErrorCode.ALREADY_CLOSED_EXCEPTION;
import static com.a303.consultms.global.exception.code.ErrorCode.ALREADY_FULL_CONSULTROOM;
import static com.a303.consultms.global.exception.code.ErrorCode.NOT_FOUND_CHANNEL_EXCEPTION;
import static com.a303.consultms.global.exception.code.ErrorCode.NOT_FOUND_CONSULT_EXCEPTION;
import static com.a303.consultms.global.exception.code.ErrorCode.UNAUTHORIZED_USER_EXCEPTION;

import com.a303.consultms.domain.channel.Channel;
import com.a303.consultms.domain.channel.dto.request.ChannelReq;
import com.a303.consultms.domain.channel.repository.ChannelRepository;
import com.a303.consultms.domain.consult.Consult;
import com.a303.consultms.domain.consult.ConsultCategory;
import com.a303.consultms.domain.consult.dto.request.ConsultCloseReq;
import com.a303.consultms.domain.consult.dto.request.ConsultRegisterReq;
import com.a303.consultms.domain.consult.dto.response.ConsultCategoryListRes;
import com.a303.consultms.domain.consult.dto.response.ConsultCategoryRes;
import com.a303.consultms.domain.consult.dto.response.ConsultDetailRes;
import com.a303.consultms.domain.consult.dto.response.ConsultListRes;
import com.a303.consultms.domain.consult.repository.ConsultCategoryRepository;
import com.a303.consultms.domain.consult.repository.ConsultRepository;
import com.a303.consultms.domain.member.dto.response.MemberBaseRes;
import com.a303.consultms.domain.member.dto.response.MemberRes;
import com.a303.consultms.global.api.response.BaseResponse;
import com.a303.consultms.global.client.MemberClient;
import com.a303.consultms.global.exception.BaseExceptionHandler;
import com.a303.consultms.global.exception.code.ErrorCode;
import jakarta.transaction.Transactional;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

@Log4j2
@Service
@RequiredArgsConstructor
public class ConsultServiceImpl implements ConsultService {

    private final ConsultRepository consultRepository;
    private final ConsultCategoryRepository consultCategoryRepository;
    private final MemberClient memberClient;
    private final ChannelRepository channelRepository;

    //고민상담소 전체 조회
    @Override
    public ConsultListRes getConsultingRooms() throws BaseExceptionHandler {
        // createtime 기준으로 내림차순 정렬
        List<Consult> consultList = consultRepository.findAllByOrderByCreateTimeDesc();
//        List<Consult> consultList = consultRepository.findAll();

        // Consult 객체의 리스트를 ConsultDetailRes 객체의 리스트로 변환
        List<ConsultDetailRes> consultDetailResList = consultList.stream().map(
            consult -> ConsultDetailRes.builder()
                .consultId(consult.getConsultId()).memberId(consult.getMemberId())
                .nickname(memberClient.getMember(consult.getMemberId()).getResult().nickname())
                .title(consult.getTitle()).content(consult.getContent())
                .categoryId(consult.getCategoryId()).isClosed(consult.isClosed())
                .isShared(consult.isShared()).canLike(consult.isCanLike())
                .channelId(consult.getChannelId())
                .build()).collect(Collectors.toList());

        // TODO: 전체 페이지 수를 계산하는 로직 추가 필요
//        int totalPages = 1;

        return ConsultListRes.builder().consultList(consultDetailResList)
//            .totalPages(totalPages)
            .build();
    }

    //고민상담소 등록
    @Override
    @Transactional
    public int registerConsultingRoom(ConsultRegisterReq consultRegisterReq, int memberId)
        throws BaseExceptionHandler {
        log.info("[ConsultService]: memberId = {}", memberId);

        //memberId로 memberClient에서 memberRes에 정보 담아 받아오기
        BaseResponse<MemberBaseRes> memberRes = memberClient.getMember(memberId);

        MemberBaseRes member = memberRes.getResult();

        String nickname = member.nickname();
        String title = consultRegisterReq.title();
        String content = consultRegisterReq.content();
        int categoryId = consultRegisterReq.categoryId();

        //고민상담소 등록
        Consult consult = Consult.createConsult(memberId, nickname, title, content, categoryId);
        consultRepository.save(consult);

        return consult.getConsultId();
    }

    //고민상담소에 채널 등록
    @Override
    @Transactional
//    public String registerChannel(ChannelReq channelReq, int consultId, int senderId) {
    public String registerChannel(int consultId, int senderId) {
        //senderId는 입장하는 사람 ID
        //고민상담소에 기존 채널 등록되어 있는지 확인
        Consult consult = consultRepository.findById(consultId).get();

        //등록된 채널 없으면 새로운 채널 생성하여 등록
        if (consult.getChannelId() == null) {
            Map<String, String> receiver = new HashMap<>();
            //receiver 정보 받아오기
            MemberBaseRes receiverMember = memberClient.getMember(consult.getMemberId())
                .getResult();
            receiver.put("memberId", String.valueOf(consult.getMemberId()));
            receiver.put("nickname", receiverMember.nickname());

            Map<String, String> sender = new HashMap<>();
            //sender 정보 받아오기
            MemberBaseRes senderMember = memberClient.getMember(senderId).getResult();
            sender.put("memberId", String.valueOf(senderId));
            sender.put("nickname", senderMember.nickname());

            //새로운 채널 생성
            Channel channel = Channel.createChannel(consultId, receiver, sender, new ArrayList<>());
            //채널 저장
            channelRepository.save(channel);

            //생성된 채널의 ID를 consult에 저장
            consult.setChannelId(channel.getChannelId());
            consultRepository.save(consult);

            return channel.getChannelId();
        }
        //등록된 채널 있으면 예외 발생
        else {
            throw new BaseExceptionHandler(ALREADY_FULL_CONSULTROOM);
        }
    }

    //고민상담소 개별 조회
    @Override
    public ConsultDetailRes getConsultingRoom(int consultId) {

        Consult consult = consultRepository.findById(consultId).get();

        return ConsultDetailRes.builder()
            .consultId(consultId).memberId(consult.getMemberId())
            .nickname(consult.getNickname()).title(consult.getTitle())
            .content(consult.getContent()).categoryId(consult.getCategoryId())
            .isClosed(consult.isClosed()).isShared(consult.isShared())
            .canLike(consult.isCanLike()).channelId(consult.getChannelId())
            .build();
    }

    //고민상담소 종료
    @Override
    @Transactional
    public int closeConsultingRoom(int consultId, ConsultCloseReq consultCloseReq, int memberId)
        throws BaseExceptionHandler {

        Consult consult = consultRepository.findById(consultId)
            .orElseThrow(() -> new BaseExceptionHandler(NOT_FOUND_CONSULT_EXCEPTION));
        boolean isShared = consultCloseReq.isShared();
        log.info("[ConsultService]: isShared = {}", isShared);

        //상담소를 종료하려는 사람이 권한이 없는 경우 에러 처리
        if (consult.getMemberId() != memberId) {
            throw new BaseExceptionHandler(UNAUTHORIZED_USER_EXCEPTION);
        }

        //고민상담고가 이미 닫힌 경우
        if (consult.isClosed() == true) {
            throw new BaseExceptionHandler(ALREADY_CLOSED_EXCEPTION);
        }

        consult.setClosed(true); //종료 상태로 저장
        consult.setShared(isShared); //공유여부 저장
        consultRepository.save(consult);

        //해당 고민상담소에 현재 채널에도 공유여부 저장하기
        String channelId = consult.getChannelId();
        Channel channel = channelRepository.findById(channelId)
            .orElseThrow(() -> new BaseExceptionHandler(NOT_FOUND_CHANNEL_EXCEPTION));
        channel.setShared(isShared); //현재 채널의 공유여부 저장
        channel.setClosed(true); //채널 종료여부 저장
        channelRepository.save(channel);

        return consult.getConsultId();
    }

    //고민상담소 카테고리 조회
    @Override
    public ConsultCategoryListRes getConsultCategoryList() {
        List<ConsultCategory> consultCategoryList = consultCategoryRepository.findAll();

        // ConsultCategory 객체의 리스트를 ConsultCategoryRes 객체의 리스트로 변환
        List<ConsultCategoryRes> consultCategoryRes = consultCategoryList.stream().map(
            category -> ConsultCategoryRes.builder().categoryId(category.getCategoryId())
                .categoryName(category.getCategoryName()).build()).collect(Collectors.toList());

        return ConsultCategoryListRes.builder().consultCategoryList(consultCategoryRes).build();
    }

    //고민상담소에 채널 매핑
    @Transactional
    @Override
    public void updateConsultChannel(int consultId, String channelId) {

        //channelId를 기반으로  Channel 엔티티 조회
        Channel channel = channelRepository.findById(channelId)
            .orElseThrow(() -> new IllegalArgumentException("Channel을 찾을 수 없습니다."));

        //조회된 Channel 엔티티의 consultId 업데이트
        channel.setConsultId(consultId);
        //업데이트된 Channel 엔티티 저장
        channelRepository.save(channel);

        //해당하는 고민상담소 채널에 매핑
        Consult consult = consultRepository.findById(consultId).get();
        //닫힌 고민상담소일 경우
        if (consult.isClosed()) {
            throw new BaseExceptionHandler(ErrorCode.ALREADY_CLOSED_EXCEPTION);
        }

        // 이미 채널이 존재하는 경우
        if (consult.getChannelId() != null) {
            throw new BaseExceptionHandler(ErrorCode.ALREADY_FULL_CONSULTROOM);
        }

        // 채널 정보 업데이트
        consult.setChannelId(channelId);
        consultRepository.save(consult);
    }


    //회원 존재여부 유효성 검사
    private void memberException(int memberId) {
        //멤버 존재하는지 유효성 검사 추가하기

    }
}
