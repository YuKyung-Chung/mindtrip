package com.a303.postitms.domain.likePostit.repository;

import com.a303.postitms.domain.likePostit.LikePostit;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LikePostitRepository extends JpaRepository<LikePostit, Integer> {

    LikePostit findByPostitIdAndMemberId(String postitId, int memberId);
}
