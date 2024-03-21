package com.a303.consultms.domain.consult.service;

import com.a303.consultms.domain.consult.Consult;
import com.a303.consultms.domain.consult.dto.request.ConsultRegisterReq;
import com.a303.consultms.domain.consult.dto.response.ConsultListRes;
import com.a303.consultms.domain.member.dto.response.MemberRes;
import com.a303.consultms.global.exception.BaseExceptionHandler;
import java.io.IOException;
import java.util.List;
import org.springframework.data.domain.Pageable;

public interface ConsultService {

    //전체 고민상담소 조회
    ConsultListRes getConsultingRooms() throws BaseExceptionHandler;

    //고민상담소 등록
    int registerConsultingRoom(ConsultRegisterReq consultRegisterReq, int memberId)
        throws BaseExceptionHandler, IOException;

}
