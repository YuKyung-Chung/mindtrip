package com.a303.missionms.domain.mission.repository;

import com.a303.missionms.domain.mission.Mission;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MissionRepository extends JpaRepository<Mission, Integer> {

	List<Mission> findAll();
}
