package com.a303.consultms.domain.channel.controller;

import com.a303.consultms.domain.channel.Channel;
import com.a303.consultms.domain.channel.dto.request.ChannelReq;
import com.a303.consultms.domain.channel.dto.request.PersonalChatReq;
import com.a303.consultms.domain.channel.dto.response.ChannelRes;
import com.a303.consultms.domain.channel.service.ChannelService;
import com.a303.consultms.domain.consult.service.ConsultService;
import com.a303.consultms.global.api.response.BaseResponse;
import com.a303.consultms.global.exception.code.SuccessCode;
import jakarta.transaction.Transactional;
import java.io.IOException;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
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

    //고민상담소 입장(채팅방 생성)
    @PostMapping("/enter/{consultId}")
    @Transactional
    public ResponseEntity<BaseResponse<String>> enterConsultRoom(
        @PathVariable int consultId,
        @RequestBody PersonalChatReq personalChatReq,
        @RequestHeader("x-member-id") int memberId
    ) throws IOException {

        //채팅방 조회
        Channel channel = channelService.readPersonalChatByRecevier(
            personalChatReq.receiver(),
            memberId
        );

        String channelId;

        if (channel == null) { //존재하지 않을 경우 생성
            channelId = channelService.registerPersonalChat(personalChatReq.receiver(), memberId);
        } else {
            channelId = channel.getChannelId();
        }
        System.out.println(channelId);

        // 고민상담소에 채널 정보를 업데이트
        consultService.updateConsultChannel(consultId, channelId);

        return BaseResponse.success(SuccessCode.INSERT_SUCCESS, channelId);
    }

    //참여중인 채팅방 목록
    @GetMapping
    public ResponseEntity<BaseResponse<List<ChannelRes>>> getPersonalChatMessage(
        @RequestHeader("x-member-id") int memberId
    ) {
        List<ChannelRes> channelResList = channelService.getPersonalChatList(memberId);

        return BaseResponse.success(SuccessCode.SELECT_SUCCESS, channelResList);
    }

    //개인 채팅 조회
    @GetMapping("/{channelId}")
    public ResponseEntity<BaseResponse<Channel>> getPersonalChat(
        @PathVariable String channelId,
        @RequestHeader("x-member-id") int memberId
    ){
        //채팅방 조회
        Channel channel = channelService.readPersonalChat(channelId, memberId);

        return BaseResponse.success(SuccessCode.SELECT_SUCCESS, channel);
    }

}
