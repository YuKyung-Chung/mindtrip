package com.a303.missionms.domain.mission.controller;

import com.a303.missionms.domain.dailyMission.DailyMission;
import com.a303.missionms.domain.dailyMission.service.DailyMissionService;
import com.a303.missionms.domain.mission.dto.request.MyTableMissionDTO;
import com.a303.missionms.domain.mission.dto.response.MissionListRes;
import com.a303.missionms.domain.mission.service.MissionService;
import com.a303.missionms.global.api.response.BaseResponse;
import com.a303.missionms.global.exception.code.SuccessCode;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/missions")
@RequiredArgsConstructor
@CrossOrigin("*")
public class MissionController {

	private final MissionService missionService;
	private final DailyMissionService dailyMissionService;

	//    @Operation(summary = "health check")
	@GetMapping("/welcome")
	public ResponseEntity<BaseResponse<String>> welcome() throws IOException {

		return BaseResponse.success(SuccessCode.CHECK_SUCCESS, "its missionms");
	}

	//    @Operation(summary = "미션리스트조회")
	@GetMapping("/v1")
	public ResponseEntity<BaseResponse<MissionListRes>> getMissionList() throws IOException {

		MissionListRes missionListRes = missionService.getMissionList();

		return BaseResponse.success(SuccessCode.CHECK_SUCCESS, missionListRes);
	}

	//    @Operation(summary = "마이테이블관리")
	@PutMapping("/v1/mytable")
	public ResponseEntity<BaseResponse<List<MyTableMissionDTO>>> putMyTableMissions(
		@RequestHeader("x-member-id") int memberId,
		@RequestBody HashMap<Integer, MyTableMissionDTO> myTableMissionDTOMap) throws IOException {

		for (Entry<Integer, MyTableMissionDTO> m : myTableMissionDTOMap.entrySet()) {
			System.out.println(m);
		}

		List<MyTableMissionDTO> myTableMissionDTOList = dailyMissionService.putMyTableMissions(
			memberId, myTableMissionDTOMap);

		return BaseResponse.success(SuccessCode.INSERT_SUCCESS, myTableMissionDTOList);
	}

	//    @Operation(summary = "마이테이블조회")
	@GetMapping("/v1/mytable")
	public ResponseEntity<BaseResponse<List<MyTableMissionDTO>>> putMyTableMissions(
		@RequestHeader("x-member-id") int memberId) throws IOException {

		List<MyTableMissionDTO> myTableMissionDTOList = dailyMissionService.getMyTableMissions(
			memberId);

		return BaseResponse.success(SuccessCode.INSERT_SUCCESS, myTableMissionDTOList);
	}

	//    @Operation(summary = "미션완료변경") TODO 캐시 및 배치 업데이트로 최적화 필요
	@PostMapping("/v1/mytable/{missionId}")
	public ResponseEntity<BaseResponse<Integer>> completeMission(
		@RequestHeader("x-member-id") int memberId,
		@PathVariable("missionId") int missionId) throws IOException {


		int dailyMissionId = dailyMissionService.completeMission(memberId, missionId);

		return BaseResponse.success(SuccessCode.INSERT_SUCCESS, dailyMissionId);
	}

}
