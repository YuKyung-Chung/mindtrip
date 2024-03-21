package com.a303.consultms.domain.message.service;

import com.a303.consultms.domain.message.Message;
import com.a303.consultms.global.exception.BaseExceptionHandler;
import java.io.IOException;

public interface MessageService {
    Message registerPersonalMessage(String channelId, Message messageReq) throws BaseExceptionHandler, IOException;
}
