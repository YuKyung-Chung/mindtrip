package com.a303.consultms.domain.consult.service;

import static com.a303.consultms.global.exception.code.ErrorCode.ALREADY_CLOSED_EXCEPTION;
import static com.a303.consultms.global.exception.code.ErrorCode.ALREADY_FULL_CONSULTROOM;
import static com.a303.consultms.global.exception.code.ErrorCode.CHANNEL_NOT_FOUND_EXCEPTION;
import static com.a303.consultms.global.exception.code.ErrorCode.NOT_FOUND_CHANNEL_EXCEPTION;
import static com.a303.consultms.global.exception.code.ErrorCode.NOT_FOUND_CONSULT_EXCEPTION;
import static com.a303.consultms.global.exception.code.ErrorCode.UNAUTHORIZED_USER_EXCEPTION;

import com.a303.consultms.domain.channel.Channel;
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
import com.a303.consultms.global.api.response.BaseResponse;
import com.a303.consultms.global.client.MemberClient;
import com.a303.consultms.global.exception.BaseExceptionHandler;
import jakarta.transaction.Transactional;
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

    //입장가능한 고민상담소 리스트 조회
    @Override
    public ConsultListRes getAvailableConsultingRooms() {
        // createtime 기준으로 내림차순 정렬
        List<Consult> consultList = consultRepository.findAllByChannelIdOrderByCreateTimeDesc(null);

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

        //고민상담소가 닫혀 있는지 확인 -> 닫혀있으면 에러 처리
        if(consult.isClosed()){
            throw new BaseExceptionHandler(ALREADY_CLOSED_EXCEPTION);
        }

        //고민상담소 만든 사람이 자기 자신인 경우 에러 처리
        if(consult.getMemberId() == senderId){
            throw new BaseExceptionHandler(UNAUTHORIZED_USER_EXCEPTION);
        }

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

    //공유된 고민 리스트 조회
    @Override
    public ConsultListRes getSharedConsultingRooms() throws BaseExceptionHandler {
        List<Consult> consultList = consultRepository.findAllByIsSharedOrderByCreateTimeDesc(true);

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

        return ConsultListRes.builder().consultList(consultDetailResList)
//            .totalPages(totalPages)
            .build();
    }

    //고민상담소 나가기(퇴장)
    @Override
    public void exitConsultingRoom(int consultId, int sender)
        throws BaseExceptionHandler {

        //consultId 기반으로 현재 Consult 조회
        Consult consult = consultRepository.findById(consultId)
            .orElseThrow(() -> new BaseExceptionHandler(NOT_FOUND_CONSULT_EXCEPTION));

        //channelId가 없는 경우 예외 처리
        String channelId = consult.getChannelId();
        if (channelId == null || channelId.isEmpty()) {
            throw new BaseExceptionHandler(CHANNEL_NOT_FOUND_EXCEPTION);
        }

        //고민상담소가 닫혀있는지 확인 -> 이미 닫힌 고민상담소는 나가기 불가
        if(consult.isClosed()) {
            throw new BaseExceptionHandler(ALREADY_CLOSED_EXCEPTION);
        }

        //나가려는 사람이 상담소를 연 사람인지 확인 -> 그렇다면 예외 처리
        //상담소를 연 사람은 상담 자체를 종료하거나 참여자를 내보내기만 할 수 있음
        if(consult.getMemberId() == sender){
            throw new BaseExceptionHandler(UNAUTHORIZED_USER_EXCEPTION);
        }

        //channelId가 있다면 삭제
        consult.setChannelId(null);
    }



    //회원 존재여부 유효성 검사
    private void memberException(int memberId) {
        //멤버 존재하는지 유효성 검사 추가하기

    }
}
