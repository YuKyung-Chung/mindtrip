package com.a303.consultms.domain.consult.repository;

import com.a303.consultms.domain.consult.Consult;
import com.a303.consultms.domain.consult.dto.response.ConsultDetailRes;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ConsultRepository extends JpaRepository<Consult, Integer> {
    ConsultDetailRes findConsultByConsultId(int consultId);
}
