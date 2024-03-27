package com.a303.notificationms.domain.Emitter.repository;

import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import org.springframework.stereotype.Repository;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

@Repository
public class EmitterRepositoryImpl implements EmitterRepository {

	private final Map<Integer, SseEmitter> sseEmitters = new ConcurrentHashMap<>();


	@Override
	public void save(int memberId, SseEmitter sseEmitter) {
		sseEmitters.put(memberId, sseEmitter);
	}

	@Override
	public void removeByMemberId(int memberId) {
		sseEmitters.remove(memberId);
	}

	@Override
	public SseEmitter findByMemberId(int memberId) {

		return sseEmitters.getOrDefault(memberId, null);

	}

	@Override
	public Map<Integer, SseEmitter> findAll() {
		return sseEmitters;
	}
}
