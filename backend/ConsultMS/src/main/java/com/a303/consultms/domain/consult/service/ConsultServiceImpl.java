package com.a303.consultms.domain.consult.service;

import com.a303.consultms.domain.consult.Consult;
import com.a303.consultms.domain.consult.dto.request.ConsultRegisterReq;
import com.a303.consultms.domain.consult.dto.response.ConsultDetailRes;
import com.a303.consultms.domain.consult.dto.response.ConsultListRes;
import com.a303.consultms.domain.consult.repository.ConsultRepository;
import com.a303.consultms.domain.member.dto.response.MemberBaseRes;
import com.a303.consultms.domain.member.dto.response.MemberRes;
import com.a303.consultms.global.api.response.BaseResponse;
import com.a303.consultms.global.client.MemberClient;
import com.a303.consultms.global.exception.BaseExceptionHandler;
import jakarta.transaction.Transactional;
import jakarta.validation.constraints.NotBlank;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Log4j2
@Service
@RequiredArgsConstructor
public class ConsultServiceImpl implements ConsultService{

    private final ConsultRepository consultRepository;
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
                .title(consult.getTitle())
                .content(consult.getContent())
                .categoryId(consult.getCategoryId())
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

        System.out.println(memberRes.getMessage());
        System.out.println(memberRes.getResult());

        String title = consultRegisterReq.title();
        String content = consultRegisterReq.content();
        int categoryId = consultRegisterReq.categoryId();

        //고민상담소 등록
        Consult consult = Consult.createConsult(memberId, title, content, categoryId);
        consultRepository.save(consult);


        return consult.getConsultId();
    }
}
