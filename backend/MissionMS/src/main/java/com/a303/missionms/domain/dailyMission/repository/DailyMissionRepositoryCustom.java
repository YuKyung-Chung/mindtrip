package com.a303.missionms.domain.dailyMission.repository;

import com.a303.missionms.domain.mission.dto.response.MissionLogRes;
import java.util.List;

public interface DailyMissionRepositoryCustom {

	List<MissionLogRes> findAllToMissionLogRes();


}
