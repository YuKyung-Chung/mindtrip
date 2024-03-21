package com.a303.postitms.domain.postit.controller;

import com.a303.postitms.domain.postit.dto.reponse.PostitTopicListRes;
import com.a303.postitms.domain.postit.dto.request.PostitRegistReq;
import com.a303.postitms.domain.postit.service.PostitService;
import com.a303.postitms.global.api.response.BaseResponse;
import com.a303.postitms.global.exception.code.SuccessCode;
import jakarta.validation.Valid;
import java.util.Date;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
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
@Slf4j
public class PostitController {

    private final PostitService postitService;

    @GetMapping("/v1")
    public ResponseEntity<BaseResponse<PostitTopicListRes>> readPostitList(
        @RequestParam String date,
        @RequestParam String order,
        @RequestParam int village,
        @RequestHeader("x-member-id") int memberId) {

        PostitTopicListRes postitTopicListRes = postitService.readPostitList(date, order, village);

        return BaseResponse.success(SuccessCode.SELECT_SUCCESS, postitTopicListRes);
    }

    @PostMapping("/v1")
    public ResponseEntity<BaseResponse<String>> registerPostit(
        @Valid @RequestBody PostitRegistReq postitRegistReq,
        @RequestHeader("x-member-id") int memberId) {

        log.debug("postits/v1 POST api accepted");

        String postitId = postitService.registerPostit(postitRegistReq, memberId);

        log.debug("postits/v1 POST api succeed");

        return BaseResponse.success(SuccessCode.INSERT_SUCCESS, postitId);
    }

    @DeleteMapping("/v1")
    public ResponseEntity<BaseResponse<String>> deletePostit(
        @RequestParam String postitId,
        @RequestParam String date,
        @RequestHeader("x-member-id") int memberId) {

        log.debug("postits/v1?postitId={}&date={} DELETE api accepted");

        postitService.deletePostit(postitId, date, memberId);

        log.debug("postits/v1?postitId={}&date={} DELETE api succeed");

        return BaseResponse.success(SuccessCode.DELETE_SUCCESS, postitId);
    }
}
