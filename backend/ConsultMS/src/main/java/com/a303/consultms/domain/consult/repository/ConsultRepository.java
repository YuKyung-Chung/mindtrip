package com.a303.consultms.domain.consult.repository;

import com.a303.consultms.domain.consult.Consult;
import com.a303.consultms.domain.consult.dto.response.ConsultDetailRes;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ConsultRepository extends JpaRepository<Consult, Integer> {
    ConsultDetailRes findConsultByConsultId(int consultId);

    //날짜 내림차순으로 정렬
    List<Consult> findAllByOrderByCreateTimeDesc();
}
