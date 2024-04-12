package com.a303.memberms.domain.member.repository;

import com.a303.memberms.domain.member.Member;
import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.EntityManager;
import java.util.HashMap;
import java.util.Map;
import org.springframework.stereotype.Repository;

@Repository
public class MemberRepositoryImpl implements MemberRepositoryCustom {

	private final JPAQueryFactory queryFactory;
	private final Map<Integer, Integer> levelMap = new HashMap<>();


	public MemberRepositoryImpl(EntityManager em) {
		this.queryFactory = new JPAQueryFactory(em);
		levelMap.put(3, 2);
		levelMap.put(9, 3);
		levelMap.put(15, 4);
		levelMap.put(21, 5);
		levelMap.put(30, 6);

	}


	@Override
	public void increaseLevel(Member member, int missionCount) {
		if (levelMap.containsKey(missionCount)) {
			int newLevel = levelMap.get(missionCount);
			member.setLevel(newLevel);
		}
	}
}
