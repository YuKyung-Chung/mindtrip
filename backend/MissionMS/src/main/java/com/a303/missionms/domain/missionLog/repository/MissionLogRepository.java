package com.a303.missionms.domain.missionLog.repository;

import com.a303.missionms.domain.missionLog.MissionLog;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface MissionLogRepository extends JpaRepository<MissionLog, Integer>,
	MissionLogRepositoryCustom {

	@Query("select ml from MissionLog ml where ml.memberId=:memberId and FUNCTION('YEAR', ml.missionDate)=:year and FUNCTION('MONTH', ml.missionDate)=:month")
	List<MissionLog> getMissionLogByMemberIdAndYearAndMonth(
		@Param("memberId") int memberId, @Param("year") int year, @Param("month") int month
	);

	Long countByMemberIdAndIsFinishIsTrue(int memberId);

}
