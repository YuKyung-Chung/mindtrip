package com.a303.villagems.domain.village.service;

import com.a303.villagems.domain.village.dto.response.VillageBaseRes;

public interface VillageService {

	VillageBaseRes findByVillageId(int villageId);
}
