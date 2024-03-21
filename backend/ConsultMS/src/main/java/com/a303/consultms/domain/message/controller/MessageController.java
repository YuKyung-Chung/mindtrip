package com.a303.consultms.domain.message.controller;

import com.a303.consultms.domain.message.Message;
import com.a303.consultms.domain.message.dto.ChatMessage;
import com.a303.consultms.domain.message.service.MessageService;
import lombok.RequiredArgsConstructor;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/chat/v1")
@RequiredArgsConstructor
@CrossOrigin("*")
public class MessageController {

//    private final MessageService messageService;

//    @MessageMapping("/{channelId}")
//    @SendTo("/channel/{channelId}")
//    public ChatMessage registerMessage(@DestinationVariable String channelId, ChatMessage chatMessage){
//
//        //채팅 저장
//        Message message = messageService.registerMessage(channelId, chatMessage.sender(), chatMessage.);
//
//        return ChatMessage.builder()
//            .channelId(channelId)
//            .sender(message.getSender())
//            .memberId(message.getMemberId())
//            .content(message.getContent())
//            .build();
//    }

}
