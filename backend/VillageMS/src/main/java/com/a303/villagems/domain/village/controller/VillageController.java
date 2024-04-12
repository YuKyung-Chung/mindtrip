package com.a303.villagems.domain.village.controller;

import com.a303.villagems.domain.village.dto.response.VillageBaseRes;
import com.a303.villagems.domain.village.service.VillageService;
import com.a303.villagems.global.api.response.BaseResponse;
import com.a303.villagems.global.exception.code.SuccessCode;
import java.io.IOException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/villages")
@RequiredArgsConstructor
@CrossOrigin("*")
@Slf4j
public class VillageController {

	private final VillageService villageService;

	@GetMapping("/v0")
	public ResponseEntity<BaseResponse<VillageBaseRes>> getVillage(
		@RequestParam int villageId
	) throws IOException {

		log.debug("villages/v0 GET api accepted");

		VillageBaseRes villageBaseRes = villageService.findByVillageId(villageId);

//		log.info("리뷰 답글 알림 전송. userId : {}, message : {}",userId, message);

		return BaseResponse.success(SuccessCode.CHECK_SUCCESS, villageBaseRes);
	}
}
