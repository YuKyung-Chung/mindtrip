package com.a303.memberms.domain.member.controller;

import com.a303.memberms.domain.member.dto.response.MemberBaseRes;
import com.a303.memberms.domain.member.repository.MemberRepository;
import com.a303.memberms.domain.member.service.MemberService;
import com.a303.memberms.global.api.response.BaseResponse;
import com.a303.memberms.global.exception.code.SuccessCode;
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
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/members")
@RequiredArgsConstructor
@CrossOrigin("*")
public class MemberController {

	private final MemberService memberService;

	//    @Operation(summary = "health check")
	@GetMapping("/welcome")
	public ResponseEntity<BaseResponse<String>> welcome() throws IOException {

		return BaseResponse.success(SuccessCode.CHECK_SUCCESS, "its memberms");
	}

	//    @Operation(summary = "멤버아이디로 멤버 조회")
	@GetMapping("/v1/{memberId}")
	public ResponseEntity<BaseResponse<MemberBaseRes>> getMissionList(
		@PathVariable("memberId") int memberId
	) throws IOException {

		MemberBaseRes memberBaseRes = memberService.getMemberByMemberId(memberId);

		return BaseResponse.success(SuccessCode.CHECK_SUCCESS, memberBaseRes);
	}

//	//    @Operation(summary = "마이테이블관리")
//	@PutMapping("/v1/mytable")
//	public ResponseEntity<BaseResponse<List<MyTableMissionDTO>>> putMyTableMissions(
//		@RequestHeader("x-member-id") int memberId,
//		@RequestBody HashMap<Integer, MyTableMissionDTO> myTableMissionDTOMap) throws IOException {
//
//		for (Entry<Integer, MyTableMissionDTO> m : myTableMissionDTOMap.entrySet()) {
//			System.out.println(m);
//		}
//
//		List<MyTableMissionDTO> myTableMissionDTOList = dailyMissionService.putMyTableMissions(
//			memberId, myTableMissionDTOMap);
//
//		return BaseResponse.success(SuccessCode.INSERT_SUCCESS, myTableMissionDTOList);
//	}
//
//	//    @Operation(summary = "마이테이블조회")
//	@GetMapping("/v1/mytable")
//	public ResponseEntity<BaseResponse<List<MyTableMissionDTO>>> putMyTableMissions(
//		@RequestHeader("x-member-id") int memberId) throws IOException {
//
//		List<MyTableMissionDTO> myTableMissionDTOList = dailyMissionService.getMyTableMissions(
//			memberId);
//
//		return BaseResponse.success(SuccessCode.INSERT_SUCCESS, myTableMissionDTOList);
//	}
//
//	//    @Operation(summary = "미션완료변경") TODO 캐시 및 배치 업데이트로 최적화 필요
//	@PostMapping("/v1/mytable/{missionId}")
//	public ResponseEntity<BaseResponse<Integer>> completeMission(
//		@RequestHeader("x-member-id") int memberId,
//		@PathVariable("missionId") int missionId) throws IOException {
//
//
//		int dailyMissionId = dailyMissionService.completeMission(memberId, missionId);
//
//		return BaseResponse.success(SuccessCode.INSERT_SUCCESS, dailyMissionId);
//	}

}
