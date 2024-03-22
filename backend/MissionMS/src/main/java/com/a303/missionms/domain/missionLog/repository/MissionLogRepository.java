package com.a303.missionms.domain.missionLog.repository;

import com.a303.missionms.domain.missionLog.MissionLog;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MissionLogRepository extends JpaRepository<MissionLog, Integer> {

}
