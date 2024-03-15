package com.a303.missionms.domain.mission.dto.response;

import com.a303.missionms.domain.mission.Category;
import lombok.Builder;

@Builder
public record MissionBaseRes(
	int missionId,
	String name,
	Category category
) {

}
