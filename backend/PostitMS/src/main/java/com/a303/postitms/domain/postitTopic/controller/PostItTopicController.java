package com.a303.postitms.domain.postitTopic.controller;

import com.a303.postitms.domain.postitTopic.PostitTopic;
import com.a303.postitms.domain.postitTopic.dto.reponse.PostitTopicRes;
import com.a303.postitms.domain.postitTopic.service.PostitTopicService;
import com.a303.postitms.global.api.response.BaseResponse;
import com.a303.postitms.global.exception.code.SuccessCode;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/postits")
@RequiredArgsConstructor
@CrossOrigin("*")
public class PostItTopicController {

    private final PostitTopicService postitTopicService;

    @GetMapping("/v1/topics")
    public ResponseEntity<BaseResponse<List<PostitTopic>>> getPostitTopic() {
        List<PostitTopic> postitTopicRes = postitTopicService.getPostitTopic();

        return BaseResponse.success(SuccessCode.SELECT_SUCCESS, postitTopicRes);
    }
}
