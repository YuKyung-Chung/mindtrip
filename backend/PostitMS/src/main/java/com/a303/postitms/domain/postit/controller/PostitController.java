package com.a303.postitms.domain.postit.controller;

import com.a303.postitms.domain.postit.dto.request.PostitRegistReq;
import com.a303.postitms.domain.postit.service.PostitService;
import com.a303.postitms.global.api.response.BaseResponse;
import com.a303.postitms.global.exception.code.SuccessCode;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/postits")
@RequiredArgsConstructor
@CrossOrigin("*")
public class PostitController {

    private final PostitService postitService;

    @PostMapping("/v1")
    public ResponseEntity<BaseResponse<String>> registerPostit(
        @Valid @RequestBody PostitRegistReq postitRegistReq,
        @RequestHeader("x-member-id") int memberId) {

        String postitId = postitService.registerPostit(postitRegistReq, memberId);

        return BaseResponse.success(SuccessCode.INSERT_SUCCESS, postitId);
    }

    @DeleteMapping("/v1")
    public ResponseEntity<BaseResponse<String>> deletePostit(
        @RequestParam String postitId,
        @RequestHeader("x-member-id") int memberId) {

        postitService.deletePostit(postitId, memberId);

        return BaseResponse.success(SuccessCode.DELETE_SUCCESS, postitId);
    }
}
