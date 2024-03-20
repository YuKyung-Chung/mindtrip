package com.a303.consultms.domain.consult.service;

import static com.a303.consultms.global.exception.code.ErrorCode.ALREADY_CLOSED_EXCEPTION;
import static com.a303.consultms.global.exception.code.ErrorCode.NOT_FOUND_CONSULT_EXCEPTION;

import com.a303.consultms.domain.consult.Consult;
import com.a303.consultms.domain.consult.ConsultCategory;
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
import com.a303.consultms.global.exception.code.ErrorCode;
import jakarta.transaction.Transactional;
import java.io.IOException;
import java.util.List;
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

    //고민상담소 전체 조회
    @Override
    public ConsultListRes getConsultingRooms() throws BaseExceptionHandler {

        List<Consult> consultList = consultRepository.findAll();

        // Consult 객체의 리스트를 ConsultDetailRes 객체의 리스트로 변환
        List<ConsultDetailRes> consultDetailResList = consultList.stream()
            .map(consult -> ConsultDetailRes.builder()
                .consultId(consult.getConsultId())
                .memberId(consult.getMemberId())
                .nickname(memberClient.getMember(consult.getMemberId()).getResult().nickname())
                .title(consult.getTitle())
                .content(consult.getContent())
                .categoryId(consult.getCategoryId())
                .isClosed(consult.isClosed())
                .channelId(consult.getChannelId())
                .build())
            .collect(Collectors.toList());

        // 전체 페이지 수를 계산하는 로직 추가 필요
//        int totalPages = 1;

        return ConsultListRes.builder()
            .consultList(consultDetailResList)
//            .totalPages(totalPages)
            .build();
    }

    //고민상담소 등록
    @Override
    @Transactional
    public int registerConsultingRoom(ConsultRegisterReq consultRegisterReq, int memberId)
        throws BaseExceptionHandler, IOException {
        log.info("[ConsultService]: memberId = {}", memberId);

        //memberId로 memberClient에서 memberRes에 정보 담아 받아오기
        BaseResponse<MemberBaseRes> memberRes = memberClient.getMember(memberId);

        MemberBaseRes member = memberRes.getResult();

        System.out.println(memberRes.getMessage());
        System.out.println(memberRes.getResult());

        String nickname = member.nickname();
        String title = consultRegisterReq.title();
        String content = consultRegisterReq.content();
        int categoryId = consultRegisterReq.categoryId();

        //고민상담소 등록
        Consult consult = Consult.createConsult(memberId, nickname, title, content, categoryId);
        consultRepository.save(consult);

        return consult.getConsultId();
    }

    //고민상담소 개별 조회
    @Override
    public ConsultDetailRes getConsultingRoom(int consultId) {

        ConsultDetailRes consultDetailRes = consultRepository.findConsultByConsultId(consultId);

        return ConsultDetailRes.builder()
            .consultId(consultId)
            .memberId(consultDetailRes.memberId())
            .nickname(consultDetailRes.nickname())
            .title(consultDetailRes.title())
            .content(consultDetailRes.content())
            .categoryId(consultDetailRes.categoryId())
            .isClosed(consultDetailRes.isClosed())
            .channelId(consultDetailRes.channelId())
            .build();
    }

    //고민상담소 종료
    @Override
    public int closeConsultingRoom(int consultId)
        throws BaseExceptionHandler {

        Consult consult = consultRepository.findById(consultId).get();

        if (consult == null) {
            throw new BaseExceptionHandler(NOT_FOUND_CONSULT_EXCEPTION);
        }
        if (consult.isClosed() == true) {
            throw new BaseExceptionHandler(ALREADY_CLOSED_EXCEPTION);
        } else {
            consult.setClosed(true);
            consultRepository.save(consult);
        }

        return consult.getConsultId();
    }

    //고민상담소 카테고리 조회
    @Override
    public ConsultCategoryListRes getConsultCategoryList() {
        List<ConsultCategory> consultCategoryList = consultCategoryRepository.findAll();

        // ConsultCategory 객체의 리스트를 ConsultCategoryRes 객체의 리스트로 변환
        List<ConsultCategoryRes> consultCategoryRes = consultCategoryList.stream()
            .map(category -> ConsultCategoryRes.builder()
                .categoryId(category.getCategoryId())
                .categoryName(category.getCategoryName())
                .build())
            .collect(Collectors.toList());

        return ConsultCategoryListRes.builder()
            .consultCategoryList(consultCategoryRes)
            .build();
    }

    @Override
    public void updateConsultChannel(int consultId, String channelId) {

        //해당하는 고민상담소 채널에 매핑
        Consult consult = consultRepository.findById(consultId).get();

        // 이미 채널이 존재하는 경우
        if (consult.getChannelId() != null) {
            throw new BaseExceptionHandler(ErrorCode.ALREADY_FULL_CONSULTROOM);
        }

        //닫힌 고민상담소일 경우
        if(consult.isClosed()){
            throw new BaseExceptionHandler(ErrorCode.ALREADY_CLOSED_EXCEPTION);
        }

        // 채널 정보 업데이트
        consult.setChannelId(channelId);
        consultRepository.save(consult);
    }
}
