package com.a303.missionms.domain.dailyMission.service;

import com.a303.missionms.domain.mission.dto.request.MyTableMissionDTO;
import com.a303.missionms.global.exception.BaseExceptionHandler;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;

public interface DailyMissionService {

	List<MyTableMissionDTO> putMyTableMissions(int memberId,
		HashMap<Integer, MyTableMissionDTO> myTableMissionDTOMap)
		throws BaseExceptionHandler, IOException;

	List<MyTableMissionDTO> getMyTableMissions(int memberId)
		throws BaseExceptionHandler, IOException;

	int completeMission(int memberId, int missionId) throws BaseExceptionHandler, IOException;


}
