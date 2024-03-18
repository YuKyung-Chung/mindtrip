package com.a303.missionms.domain.dailyMission.repository;

import com.a303.missionms.domain.dailyMission.DailyMission;
import com.a303.missionms.domain.mission.Mission;
import java.util.List;
import java.util.Optional;
import javax.swing.text.html.Option;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface DailyMissionRepository extends JpaRepository<DailyMission, Integer> {
//	List<DailyMission> findByMemberIdAndIsFinish(int memberId, boolean isFinish);

	List<DailyMission> findByMemberId(int memberId);

	Optional<DailyMission> findByMemberIdAndMission_MissionId(int memberId, int missionId);

}
