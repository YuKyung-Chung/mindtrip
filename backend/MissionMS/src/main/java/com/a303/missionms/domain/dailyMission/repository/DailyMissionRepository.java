package com.a303.missionms.domain.dailyMission.repository;

import com.a303.missionms.domain.dailyMission.DailyMission;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface DailyMissionRepository extends JpaRepository<DailyMission, Integer>,
	DailyMissionRepositoryCustom {

	//	List<DailyMission> findByMemberIdAndIsFinish(int memberId, boolean isFinish);
	List<DailyMission> findAll();

	@Query("select dm from DailyMission dm join fetch dm.mission where dm.memberId=:memberId")
	List<DailyMission> findByMemberId(@Param("memberId") int memberId);

	Optional<DailyMission> findByMemberIdAndMission_MissionId(int memberId, int missionId);

	Long countByMemberIdAndIsFinishIsTrue(int memberId);


}
