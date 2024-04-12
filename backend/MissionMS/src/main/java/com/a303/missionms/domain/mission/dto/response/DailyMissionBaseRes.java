package com.a303.missionms.domain.mission.dto.response;

import lombok.Builder;

@Builder
public record DailyMissionBaseRes(
	int missionId,
	int memberId,
	boolean isFinish
) {

}
