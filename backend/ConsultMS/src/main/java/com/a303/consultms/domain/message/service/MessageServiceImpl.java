package com.a303.consultms.domain.message.service;

import com.a303.consultms.domain.channel.Channel;
import com.a303.consultms.domain.channel.repository.ChannelRepository;
import com.a303.consultms.domain.message.Message;
import com.a303.consultms.domain.message.repository.MessageRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MessageServiceImpl {

    private final MessageRepository messageRepository;
    private final ChannelRepository channelRepository;

    public Message registerMessage(String channelId, Message messageReq) {
        Message message = Message.createMessage(messageReq.getSender(), messageReq.getContent());
        Channel channel = channelRepository.findById(channelId).get();

        messageRepository.save(message);
        channel.getMessageList().add(message);

        channelRepository.save(channel);

        return message;
    }
}
