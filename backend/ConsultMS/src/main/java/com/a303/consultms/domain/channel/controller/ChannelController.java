package com.a303.consultms.domain.channel.controller;

import com.a303.consultms.domain.channel.service.ChannelService;
import com.a303.consultms.domain.consult.service.ConsultService;
import com.a303.consultms.global.api.response.BaseResponse;
import com.a303.consultms.global.exception.code.SuccessCode;
import jakarta.transaction.Transactional;
import java.io.IOException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/channels/v1")
@RequiredArgsConstructor
@CrossOrigin("*")
public class ChannelController {

    private final ChannelService channelService;
    private final ConsultService consultService;

    //고민상담소 입장
    @PostMapping("/enter/{consultId}")
    @Transactional
    public ResponseEntity<BaseResponse<String>> enterConsultRoom(
        @PathVariable int consultId,
        @RequestHeader("x-member-id") int memberId
    ) throws IOException {

        String channelId = channelService.enterConsultingRoom(consultId, memberId);

        // 고민상담소에 채널 정보를 업데이트
        consultService.updateConsultChannel(consultId, channelId);

        return BaseResponse.success(SuccessCode.INSERT_SUCCESS, channelId);
    }
}
