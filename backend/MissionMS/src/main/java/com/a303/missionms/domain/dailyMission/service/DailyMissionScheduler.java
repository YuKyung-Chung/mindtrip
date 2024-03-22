package com.a303.missionms.domain.dailyMission.service;

import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class DailyMissionScheduler {

	private final DailyMissionService dailyMissionService;

	@Scheduled(cron = "5 0 12 * * *")  // 매일 12시 00분 5초
	@Transactional
	public void dailyMissionRecommend() {
		dailyMissionService.dailyMissionRecommend();
	}
}