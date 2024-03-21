package com.a303.memberms.domain.member.repository;

import com.a303.memberms.domain.member.Member;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<Member, Integer> {

	Optional<Member> findByMemberId(int memberId);

}
