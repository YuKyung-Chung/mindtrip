package com.a303.missionms.domain.missionLog.service;

import com.a303.missionms.domain.mission.dto.response.MissionReportRes;

public interface MissionLogService {

	MissionReportRes getMissionReport(int memberId, int year, int month);

	long getCompletedMissionCount(int memberId);
}
