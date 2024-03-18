package com.a303.missionms.domain.mission.dto.request;

import lombok.Builder;

@Builder
public record MyTableMissionDTO(
	int missionId,
	boolean isFinish
) {

}
