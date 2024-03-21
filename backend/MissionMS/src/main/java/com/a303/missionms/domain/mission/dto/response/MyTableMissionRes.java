package com.a303.missionms.domain.mission.dto.response;

import lombok.Builder;

@Builder
public record MyTableMissionRes(
        int missionId,
        String name,
        boolean isFinish
) {

}
