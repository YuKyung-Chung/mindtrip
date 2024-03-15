package com.a303.missionms.domain.mission.controller;

import com.a303.missionms.domain.mission.dto.response.MissionListRes;
import com.a303.missionms.domain.mission.service.MissionService;
import com.a303.missionms.global.api.response.BaseResponse;
import com.a303.missionms.global.exception.code.SuccessCode;
import java.io.IOException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/missions")
@RequiredArgsConstructor
@CrossOrigin("*")
public class MissionController {

	private final MissionService missionService;

	//    @Operation(summary = "health check")
	@GetMapping("/welcome")
	public ResponseEntity<BaseResponse<String>> welcome() throws IOException {

		return BaseResponse.success(SuccessCode.CHECK_SUCCESS, "its missionms");
	}

	@GetMapping("/v1")
	public ResponseEntity<BaseResponse<MissionListRes>> getMissionList() throws IOException {

		MissionListRes missionListRes = missionService.getMissionList();

		return BaseResponse.success(SuccessCode.CHECK_SUCCESS, missionListRes);
	}

//    @Operation(summary = "상품구매")
//    @GetMapping("/buy")
//    public ResponseEntity<BaseResponse<Integer>> registerReview(
//            @RequestParam("productName") String productName) throws IOException {
//
//        orderService.insertOrder(productName);
//
//        return BaseResponse.success(SuccessCode.INSERT_SUCCESS, 1);
//    }
}
