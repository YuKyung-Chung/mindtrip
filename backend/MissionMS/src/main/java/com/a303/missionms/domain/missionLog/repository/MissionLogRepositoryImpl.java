package com.a303.missionms.domain.missionLog.repository;

import static com.a303.missionms.domain.mission.QMission.mission;
import static com.a303.missionms.domain.missionLog.QMissionLog.missionLog;

import com.a303.missionms.domain.mission.dto.response.MissionBaseRes;
import com.a303.missionms.domain.mission.dto.response.MissionLogBaseRes;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.EntityManager;
import java.util.List;
import org.springframework.stereotype.Repository;

@Repository
public class MissionLogRepositoryImpl implements MissionLogRepositoryCustom {

	private final JPAQueryFactory queryFactory;

	public MissionLogRepositoryImpl(EntityManager em) {
		this.queryFactory = new JPAQueryFactory(em);
	}


	@Override
	public List<MissionLogBaseRes> getMyReport(int memberId, int year, int month) {
		return queryFactory.select(
				Projections.constructor(MissionLogBaseRes.class,
					missionLog.missionLogId,
					Projections.constructor(MissionBaseRes.class,
						mission.missionId,
						mission.name,
						mission.category
					),
					missionLog.memberId,
					missionLog.missionDate,
					missionLog.isFinish)
			)
			.from(missionLog)
			.join(mission).on(mission.missionId.eq(missionLog.mission.missionId))
			.fetch();
	}
}
