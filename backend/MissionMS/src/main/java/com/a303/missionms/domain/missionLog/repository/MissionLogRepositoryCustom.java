package com.a303.missionms.domain.missionLog.repository;

import com.a303.missionms.domain.mission.dto.response.MissionLogBaseRes;
import java.util.List;
import org.springframework.stereotype.Repository;

@Repository
public interface MissionLogRepositoryCustom {

	List<MissionLogBaseRes> getMyReport(int memberId, int year, int month);
//	List<MissionLogBaseRes> getMyDailMissionForReport(int memberId);


}
