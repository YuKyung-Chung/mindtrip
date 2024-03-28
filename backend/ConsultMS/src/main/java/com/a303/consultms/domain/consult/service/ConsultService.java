package com.a303.consultms.domain.consult.service;

import com.a303.consultms.domain.channel.dto.request.ChannelReq;
import com.a303.consultms.domain.consult.dto.request.ConsultCloseReq;
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
        throws BaseExceptionHandler;

    //고민상담소에 채널 등록
//    String registerChannel(ChannelReq channelReq, int consultId, int sender);
    String registerChannel(int consultId, int sender);

    //고민상담소 개별 조회
    ConsultDetailRes getConsultingRoom(int consultId);

    //고민상담 종료
    int closeConsultingRoom(int consultId, ConsultCloseReq consultCloseReq, int memberId)
        throws BaseExceptionHandler;

    //고민상담소 카테고리 조회
    ConsultCategoryListRes getConsultCategoryList();

//    void updateConsultChannel(int consultId, String channelId);

    //공유된 고민 리스트 조회
    ConsultListRes getSharedConsultingRooms();

    //고민상담소 나가기(퇴장)
    void exitConsultingRoom(int consultId, int sender);

    //입장가능한 고민상담소 리스트 조회
    ConsultListRes getAvailableConsultingRooms();

    //참여자 강제로 추방시키기
    void expelConsultingRoom(int consultId, int sender);
}
