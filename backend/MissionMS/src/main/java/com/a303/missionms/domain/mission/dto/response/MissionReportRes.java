package com.a303.missionms.domain.mission.dto.response;

import java.util.Map;
import lombok.Builder;

@Builder
public record MissionReportRes(
	int percent,
	int year,
	int month,
	Map<Integer, CalenderDayMissionRes> missionCalender
) {

}
