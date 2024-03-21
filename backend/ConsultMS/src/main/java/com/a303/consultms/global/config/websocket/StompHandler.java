package com.a303.consultms.global.config.websocket;


import com.a303.consultms.domain.channel.service.ChannelService;
import com.a303.consultms.domain.consult.service.ConsultService;
import java.util.Objects;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.simp.stomp.StompCommand;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import org.springframework.messaging.support.ChannelInterceptor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Order(Ordered.HIGHEST_PRECEDENCE + 99)
@Slf4j
public class StompHandler implements ChannelInterceptor {

    private final ConsultService consultService;
    private final ChannelService channelService;

    @Override
    public Message<?> preSend(Message<?> message, MessageChannel channel) {
        StompHeaderAccessor accessor = StompHeaderAccessor.wrap(message);

        // StompCommand에 따라서 로직을 분기해서 처리하는 메서드를 호출한다.
        log.info("StompAccessor = {}", accessor);
        handleMessage(accessor.getCommand(), accessor);

        return message;
    }

    private void handleMessage(StompCommand stompCommand, StompHeaderAccessor accessor) {
        switch (stompCommand) {

            case CONNECT:
//                connectToChannel(accessor);
                System.out.println("클라이언트가 연결되었습니다.");
                break;
            case SUBSCRIBE:
                System.out.println("클라이언트가 구독했습니다.");
            case SEND:
                System.out.println("메시지가 전송되었습니다.");
                break;
        }
    }
    private String getAccessToken(StompHeaderAccessor accessor) {
        return accessor.getFirstNativeHeader("x-member-id");
    }

    private void connectToChannel(StompHeaderAccessor accessor) {
//        // 채팅방 번호를 가져온다.
//        String channelId = String.valueOf(getChannelId(accessor));
//
//        // 채팅방 입장 처리
//        consultService.connectChatRoom(channelId);
//        // 읽지 않은 채팅을 전부 읽음 처리
//        channelService.updateCountAllZero(channelId);
//        // 현재 채팅방에 접속중인 인원이 있는지 확인한다.
//        boolean isConnected = consultService.isConnected(channelId);
//
//        if (isConnected) {
//            channelService.updateMessage(channelId);
//        }
    }


    private Integer getChannelId(StompHeaderAccessor accessor) {
        return
            Integer.valueOf(
                Objects.requireNonNull(
                    accessor.getFirstNativeHeader("channelId")
                ));
    }
}
