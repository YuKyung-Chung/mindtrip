package com.a303.memberms.domain.member.repository;

import com.a303.memberms.domain.member.Member;
import jakarta.annotation.Nonnull;
import java.util.Optional;
import lombok.NonNull;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<Member, Integer> {

	Optional<Member> findByMemberId(int memberId);


    Optional<Member> findById(@Nonnull String id);

    boolean existsById(@NonNull String id);

    boolean existsByNickname(@Nonnull String nickname);
}
