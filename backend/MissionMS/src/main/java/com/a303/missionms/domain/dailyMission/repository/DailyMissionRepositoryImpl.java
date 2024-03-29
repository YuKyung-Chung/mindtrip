package com.a303.missionms.domain.dailyMission.repository;

import static com.a303.missionms.domain.dailyMission.QDailyMission.dailyMission;

import com.a303.missionms.domain.mission.dto.response.MissionLogRes;
import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.Expressions;
import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.EntityManager;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

@Slf4j
@Repository
public class DailyMissionRepositoryImpl implements DailyMissionRepositoryCustom {

	private final JPAQueryFactory queryFactory;

	public DailyMissionRepositoryImpl(EntityManager em) {
		this.queryFactory = new JPAQueryFactory(em);
	}


	@Override
	public List<MissionLogRes> findAllToMissionLogRes() {
		return queryFactory.select(
				Projections.constructor(MissionLogRes.class,
					dailyMission.memberId,
					dailyMission.mission.missionId,
					dailyMission.isFinish,
					dailyMission.createTime
				)
			)
			.from(dailyMission)
			.fetch();
	}
}
