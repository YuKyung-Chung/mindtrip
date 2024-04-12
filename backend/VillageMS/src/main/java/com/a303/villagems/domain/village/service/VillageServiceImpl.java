package com.a303.villagems.domain.village.service;

import com.a303.villagems.domain.village.Village;
import com.a303.villagems.domain.village.dto.response.VillageBaseRes;
import com.a303.villagems.domain.village.repository.VillageRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
@Transactional
public class VillageServiceImpl implements VillageService {

	private final VillageRepository villageRepository;

	@Override
	public VillageBaseRes findByVillageId(int villageId) {

		Village village = villageRepository.findByVillageId(villageId);

		return VillageBaseRes.builder()
			.villageId(villageId)
			.villageName(village.getName())
			.build();
	}
}
