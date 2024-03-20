package com.a303.consultms.domain.consult.service;

import com.a303.consultms.domain.consult.dto.request.ConsultRegisterReq;
import com.a303.consultms.domain.consult.dto.response.ConsultCategoryListRes;
import com.a303.consultms.domain.consult.dto.response.ConsultDetailRes;
import com.a303.consultms.domain.consult.dto.response.ConsultListRes;
import com.a303.consultms.global.exception.BaseExceptionHandler;
import java.io.IOException;

public interface ConsultService {

    //전체 고민상담소 조회
    ConsultListRes getConsultingRooms() throws BaseExceptionHandler;

    //고민상담소 등록
    int registerConsultingRoom(ConsultRegisterReq consultRegisterReq, int memberId)
        throws BaseExceptionHandler, IOException;

    //고민상담소 개별 조회
    ConsultDetailRes getConsultingRoom(int consultId);

    //고민상담 종료
    int closeConsultingRoom(int consultId)
        throws BaseExceptionHandler;

    //고민상담소 카테고리 조회
    ConsultCategoryListRes getConsultCategoryList();

    void updateConsultChannel(int consultId, String channelId);
}
