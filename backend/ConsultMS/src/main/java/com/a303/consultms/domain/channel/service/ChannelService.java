package com.a303.consultms.domain.channel.service;

import com.a303.consultms.domain.channel.Channel;
import com.a303.consultms.domain.channel.dto.response.ChannelRes;
import com.a303.consultms.global.exception.BaseExceptionHandler;
import java.util.List;

public interface ChannelService {

//    String enterConsultingRoom(int consultId, int memberId) throws BaseExceptionHandler;

    Channel readPersonalChatByRecevier(int receiver, int memberId);

    String registerPersonalChat(int receiver, int memberId);

    List<ChannelRes> getPersonalChatList(int memberId);

    Channel readPersonalChat(String channelId, int memberId);
}
