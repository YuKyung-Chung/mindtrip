package com.a303.missionms.global.client;

import com.a303.missionms.domain.member.dto.response.MemberBaseRes;
import com.a303.missionms.global.api.response.BaseResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "memberms")
public interface MemberClient {
	@GetMapping("api/members/v1/{memberId}")
	BaseResponse<MemberBaseRes> getMemberDtoByMemberId(@PathVariable int memberId);
}
