package com.a303.missionms.domain.mission.dto.response;

import java.util.List;
import lombok.Builder;

@Builder
public record CalenderDayMissionRes(
	int count,
	List<MyTableMissionRes> missions

) {

}
