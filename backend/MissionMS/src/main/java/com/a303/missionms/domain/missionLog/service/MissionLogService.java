package com.a303.missionms.domain.missionLog.service;

import com.a303.missionms.domain.mission.dto.response.MissionReportRes;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

public interface MissionLogService {

	MissionReportRes getMissionReport(int memberId, int year, int month);
}
