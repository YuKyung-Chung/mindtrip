package com.a303.postitms.domain.postit.controller;

import com.a303.postitms.domain.postit.dto.reponse.MyPostitRes;
import com.a303.postitms.domain.postit.dto.reponse.PostitTopicListRes;
import com.a303.postitms.domain.postit.dto.request.PostitRegistReq;
import com.a303.postitms.domain.postit.service.PostitService;
import com.a303.postitms.global.api.response.BaseResponse;
import com.a303.postitms.global.exception.code.SuccessCode;
import jakarta.validation.Valid;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
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
@Slf4j
public class PostitController {

    private final PostitService postitService;

    @GetMapping("/v1")
    public ResponseEntity<BaseResponse<PostitTopicListRes>> readPostitList(
        @RequestParam String date,
        @RequestParam String order,
        @RequestParam String village,
        Pageable pageable,
        @RequestHeader("x-member-id") int memberId) {

        PostitTopicListRes postitTopicListRes = postitService.readPostitList(date, order, village,
            pageable, memberId);

        return BaseResponse.success(SuccessCode.SELECT_SUCCESS, postitTopicListRes);
    }

    @GetMapping("/v1/my")
    public ResponseEntity<BaseResponse<List<MyPostitRes>>> readMyPostitList(
        @RequestHeader("x-member-id") int memberId) {

        log.debug("postits/v1/my GET api accepted");

        List<MyPostitRes> myPostitResList = postitService.readMyPostitList(memberId);

        log.debug("postits/v1/my GET api succeed");

        return BaseResponse.success(SuccessCode.SELECT_SUCCESS, myPostitResList);
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

    @PostMapping("/v1/like/{postitId}")
    public ResponseEntity<BaseResponse<String>> likePostit(
        @PathVariable String postitId,
        @RequestHeader("x-member-id") int memberId) {

        log.debug("postits/v1/like/{} POST api accepted", postitId);
        postitService.addLikesToRedis(postitId, memberId);
        log.debug("postits/v1/like/{} POST api succeed", postitId);

        return BaseResponse.success(SuccessCode.INSERT_SUCCESS, postitId);
    }

    @DeleteMapping("/v1/like/{postitId}")
    public ResponseEntity<BaseResponse<String>> deleteLikePostit(
        @PathVariable String postitId,
        @RequestHeader("x-member-id") int memberId) {

        log.debug("postits/v1/like/{} DELETE api accepted", postitId);

        postitService.deleteLikePostit(postitId, memberId);

        log.debug("postits/v1/like/{} DELETE api succeed", postitId);
        return BaseResponse.success(SuccessCode.DELETE_SUCCESS, postitId);
    }

    @PostMapping("/v1/report/{postitId}")
    public ResponseEntity<BaseResponse<String>> reportPostit(
        @PathVariable String postitId,
        @RequestHeader("x-member-id") int memberId) {

        log.debug("postits/v1/report/{} POST api accepted", postitId);
        postitService.addReportToRedis(postitId, memberId);
        log.debug("postits/v1/report/{} POST api succeed", postitId);

        return BaseResponse.success(SuccessCode.INSERT_SUCCESS, postitId);
    }

    @DeleteMapping("/v1/report/{postitId}")
    public ResponseEntity<BaseResponse<String>> deleteReportPostit(
        @PathVariable String postitId,
        @RequestHeader("x-member-id") int memberId) {

        log.debug("postits/v1/report/{} DELETE api accepted", postitId);

        postitService.deleteReportPostit(postitId, memberId);

        log.debug("postits/v1/report/{} DELETE api succeed", postitId);
        return BaseResponse.success(SuccessCode.DELETE_SUCCESS, postitId);
    }
}
