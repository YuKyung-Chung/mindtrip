package com.a303.missionms.global.client;

import com.a303.missionms.domain.member.dto.response.MemberBaseRes;
import com.a303.missionms.global.api.response.BaseResponse;
import java.util.List;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "memberms")
public interface MemberClient {

	@GetMapping("api/members/v1/{memberId}")
	BaseResponse<MemberBaseRes> getMemberDtoByMemberId(@PathVariable int memberId);

	@GetMapping("api/members/v0/id-list")
	BaseResponse<List<Integer>> getMemberIdList();

	@PutMapping("api/members/v1/mission-count")
	BaseResponse<Void> increaseMissionCount(@RequestParam int memberId);

}
