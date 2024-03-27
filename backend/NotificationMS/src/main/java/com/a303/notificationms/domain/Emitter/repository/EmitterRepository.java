package com.a303.notificationms.domain.Emitter.repository;

import jakarta.ws.rs.sse.Sse;
import java.util.Map;
import java.util.Optional;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

public interface EmitterRepository {

	void save(int memberId, SseEmitter sseEmitter);

	void removeByMemberId(int memberId);

	SseEmitter findByMemberId(int memberId);

	Map<Integer, SseEmitter> findAll();

}
