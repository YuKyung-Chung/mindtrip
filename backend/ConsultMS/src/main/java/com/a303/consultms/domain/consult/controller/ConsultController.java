package com.a303.consultms.domain.consult.controller;

import com.a303.consultms.domain.consult.Consult;
import com.a303.consultms.domain.consult.dto.request.ConsultRegisterReq;
import com.a303.consultms.domain.consult.dto.response.ConsultListRes;
import com.a303.consultms.domain.consult.service.ConsultService;
import com.a303.consultms.domain.member.dto.response.MemberRes;
import com.a303.consultms.global.api.response.BaseResponse;
import com.a303.consultms.global.exception.code.SuccessCode;
import jakarta.validation.Valid;
import java.io.IOError;
import java.io.IOException;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/consults")
@RequiredArgsConstructor
@CrossOrigin("*")
public class ConsultController {

    private final ConsultService consultService;

    //고민상담소 등록
    @PostMapping("/v1")
    public ResponseEntity<BaseResponse<Integer>> registerConsult(
        @Valid @RequestBody ConsultRegisterReq consultRegisterReq,
        @RequestHeader("x-member-id") int memberId
    ) throws IOException {

        int consultId = consultService.registerConsultingRoom(consultRegisterReq, memberId);

        return BaseResponse.success(SuccessCode.INSERT_SUCCESS, consultId);
    }

    //고민상담소 전체 조회
    @GetMapping("/v1")
    public ResponseEntity<BaseResponse<ConsultListRes>> getConsultList(
        @RequestHeader("x-member-id") int memberId
    ) {
        ConsultListRes consultListRes = consultService.getConsultingRooms();
        return BaseResponse.success(SuccessCode.SELECT_SUCCESS, consultListRes);
    }
}
