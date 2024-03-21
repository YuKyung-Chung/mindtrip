package com.a303.consultms.domain.consult.repository;

import com.a303.consultms.domain.consult.Consult;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ConsultRepository extends JpaRepository<Consult, Integer> {

}
