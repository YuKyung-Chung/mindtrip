package com.a303.consultms.domain.consult.controller;

import com.a303.consultms.domain.channel.service.ChannelService;
import com.a303.consultms.domain.consult.dto.request.ConsultCloseReq;
import com.a303.consultms.domain.consult.dto.request.ConsultRegisterReq;
import com.a303.consultms.domain.consult.dto.response.ConsultCategoryListRes;
import com.a303.consultms.domain.consult.dto.response.ConsultDetailRes;
import com.a303.consultms.domain.consult.dto.response.ConsultListRes;
import com.a303.consultms.domain.consult.service.ConsultService;
import com.a303.consultms.global.api.response.BaseResponse;
import com.a303.consultms.global.exception.code.SuccessCode;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import java.io.IOException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import zipkin2.Call.Base;

@RestController
@RequestMapping("/api/consults/v1")
@RequiredArgsConstructor
@CrossOrigin("*")
public class ConsultController {

    private final ConsultService consultService;
    private final ChannelService channelService;

    //고민상담소 등록
    @PostMapping
    @Transactional
    public ResponseEntity<BaseResponse<Integer>> registerConsult(
        @Valid @RequestBody ConsultRegisterReq consultRegisterReq,
        @RequestHeader("x-member-id") int memberId
    ) throws IOException {
        int consultId = consultService.registerConsultingRoom(consultRegisterReq, memberId);
        return BaseResponse.success(SuccessCode.INSERT_SUCCESS, consultId);
    }

    //고민상담소 전체 조회
    @GetMapping
    public ResponseEntity<BaseResponse<ConsultListRes>> getConsultList(
        @RequestHeader("x-member-id") int memberId
    ) {
        ConsultListRes consultListRes = consultService.getConsultingRooms();
        return BaseResponse.success(SuccessCode.SELECT_SUCCESS, consultListRes);
    }

    //입장가능한 고민상담소 리스트 조회(channelId = null 인 방들)
    @GetMapping("/available")
    public ResponseEntity<BaseResponse<ConsultListRes>> getAvailableConsultList(
        @RequestHeader("x-member-id") int memberId
    ){
        ConsultListRes consultListRes = consultService.getAvailableConsultingRooms();
        return BaseResponse.success(SuccessCode.SELECT_SUCCESS, consultListRes);
    }

    //고민상담소 개별 정보 조회
    @GetMapping("/detail/{consultId}")
    public ResponseEntity<BaseResponse<ConsultDetailRes>> getConsultDetail(
        @PathVariable int consultId,
        @RequestHeader("x-member-id") int memberId
    ) throws IOException {
        ConsultDetailRes consultDetailRes = consultService.getConsultingRoom(consultId);
        return BaseResponse.success(SuccessCode.SELECT_SUCCESS, consultDetailRes);
    }

    //고민상담소 종료 (isClosed = 'Y' 로 변경)
    //현재 저장된 채널의 공유여부 저장하기 (@RequestParam isShared)
    @PutMapping("/close/{consultId}")
    @Transactional
    public ResponseEntity<BaseResponse<Integer>> closeConsult(
        @PathVariable int consultId,
        @Valid @RequestBody ConsultCloseReq consultCloseReq,
        @RequestHeader("x-member-id") int memberId
    ) {
        consultService.closeConsultingRoom(consultId, consultCloseReq, memberId);
        return BaseResponse.success(SuccessCode.CLOSED_SUCCESS, consultId);
    }

    //고민상담소 카테고리 조회
    @GetMapping("/category")
    public ResponseEntity<BaseResponse<ConsultCategoryListRes>> getConsultCategoryList(
        @RequestHeader("x-member-id") int memberId
    ) {
        ConsultCategoryListRes consultCategoryList = consultService.getConsultCategoryList();
        return BaseResponse.success(SuccessCode.SELECT_SUCCESS, consultCategoryList);
    }

    //공유된 고민상담소 리스트 조회
    @GetMapping("/shared")
    public ResponseEntity<BaseResponse<ConsultListRes>> getSharedConsultList(
        @RequestHeader("x-member-id") int memberId
    ) {
        ConsultListRes consultListRes = consultService.getSharedConsultingRooms();
        return BaseResponse.success(SuccessCode.SELECT_SUCCESS, consultListRes);
    }


}
