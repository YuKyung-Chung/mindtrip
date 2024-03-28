package com.a303.postitms.domain.reportPostit.repository;


import com.a303.postitms.domain.reportPostit.ReportPostit;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReportPostitRepository extends JpaRepository<ReportPostit, Integer> {
    ReportPostit findByPostitIdAndMemberId(String postitId, int memberId);
}
