package com.example.userserver.module.domain.orders.controller;

import com.example.userserver.global.api.response.BaseResponse;
import com.example.userserver.global.exception.code.SuccessCode;
import java.io.IOException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

//@Tag(name = "review", description = "리뷰 API")
@RestController
@RequestMapping("/orders/")
@RequiredArgsConstructor
@CrossOrigin("*")
public class OrdersController {
//    @Operation(summary = "health check")
    @PostMapping("/welcome")
    public ResponseEntity<BaseResponse<String>> registerReview() throws IOException {

        return BaseResponse.success(SuccessCode.CHECK_SUCCESS, "its orderserver");
    }

//    @Operation(summary = "리뷰 등록")
//    @PostMapping("/v1/{lectureId}")
//    public ResponseEntity<BaseResponse<Integer>> registerReview(
//            @PathVariable("lectureId") int lectureId,
//            @RequestBody ReviewRegisterReq reviewRegisterReq,
//            @AuthenticationPrincipal MemberSecurityDTO memberSecurityDTO) throws IOException {
//        Member member = memberRepository.findById(memberSecurityDTO.getMemberId())
//                .orElseThrow(() -> new BaseExceptionHandler(
//                        "memberId=" + memberSecurityDTO.getMemberId() + " 인 사용자를 DB에서 찾을수없습니다.",
//                        ErrorCode.NOT_FOUND_USER_EXCEPTION));
//
//        int reviewId = reviewService.registerReview(lectureId, reviewRegisterReq, member);
//
//        return BaseResponse.success(SuccessCode.INSERT_SUCCESS, reviewId);
//    }
}
