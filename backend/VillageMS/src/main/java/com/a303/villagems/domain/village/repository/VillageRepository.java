package com.a303.villagems.domain.village.repository;

import com.a303.villagems.domain.village.Village;
import jakarta.persistence.criteria.CriteriaBuilder.In;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VillageRepository extends JpaRepository<Village, Integer> {

	Village findByVillageId(int villageId);

}
