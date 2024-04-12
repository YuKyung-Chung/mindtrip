package com.a303.consultms.domain.message.service;

import com.a303.consultms.domain.channel.Channel;
import com.a303.consultms.domain.channel.repository.ChannelRepository;
import com.a303.consultms.domain.message.Message;
import com.a303.consultms.domain.message.repository.MessageRepository;
import com.a303.consultms.global.exception.BaseExceptionHandler;
import java.io.IOException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MessageServiceImpl implements MessageService{

    private final MessageRepository messageRepository;
    private final ChannelRepository channelRepository;


    @Override
    public Message registerPersonalMessage(String channelId, Message messageReq)
        throws BaseExceptionHandler, IOException {

        Message message = Message.createMessage(messageReq.getSender(), messageReq.getText());
        Channel channel = channelRepository.findById(channelId).get();

        messageRepository.save(message);
        channel.getMessageList().add(message);

        channelRepository.save(channel);

        //TODO: 알림 서비스 로직 추가하기

        return message;
    }
}
