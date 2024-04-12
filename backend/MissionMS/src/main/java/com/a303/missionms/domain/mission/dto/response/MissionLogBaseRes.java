package com.a303.missionms.domain.mission.dto.response;

import java.time.LocalDate;
import lombok.Builder;

@Builder
public record MissionLogBaseRes(
	int missionLogId,
	MissionBaseRes missionBaseRes,
	int memberId,
	LocalDate missionDate,
	boolean isFinish
) {

}
