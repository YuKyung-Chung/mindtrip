package com.a303.missionms.domain.mission.dto.response;

import com.a303.missionms.domain.mission.Category;
import java.util.List;
import lombok.Builder;

@Builder
public record CategoryMissionRes(
	Category category,
	List<MissionBaseRes> missionBaseResList
) {

}
