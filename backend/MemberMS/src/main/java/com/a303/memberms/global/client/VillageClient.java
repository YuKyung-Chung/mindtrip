package com.a303.memberms.global.client;

import com.a303.memberms.domain.village.dto.response.VillageBaseRes;
import com.a303.memberms.global.api.response.BaseResponse;
import java.util.List;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "villagems")
public interface VillageClient {

	@GetMapping("api/villages/v0")
	BaseResponse<VillageBaseRes> getVillage(@RequestParam int villageId);

}
