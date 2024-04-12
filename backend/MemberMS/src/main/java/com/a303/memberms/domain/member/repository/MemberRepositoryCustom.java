package com.a303.memberms.domain.member.repository;

import com.a303.memberms.domain.member.Member;
import org.springframework.stereotype.Repository;

@Repository
public interface MemberRepositoryCustom {

	void increaseLevel(Member member, int missionCount);

}
