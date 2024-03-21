package com.a303.consultms.domain.channel.service;

import com.a303.consultms.global.exception.BaseExceptionHandler;

public interface ChannelService {

    String enterConsultingRoom(int consultId, int memberId) throws BaseExceptionHandler;
}
