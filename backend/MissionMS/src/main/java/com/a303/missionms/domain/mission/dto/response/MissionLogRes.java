package com.a303.missionms.domain.mission.dto.response;

import java.time.LocalDate;
import java.time.LocalDateTime;
import lombok.Builder;

@Builder
public record MissionLogRes(
	int memberId,
	int missionId,
	boolean isFinish,
	LocalDateTime missionDate
) {

}
