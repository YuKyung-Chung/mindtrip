package com.a303.notificationms.domain.notification.service;

import com.a303.notificationms.global.exception.BaseExceptionHandler;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

public interface NotificationService {

	SseEmitter subscribe(int memberId);

	void notifyCnt(int memberId);




}
