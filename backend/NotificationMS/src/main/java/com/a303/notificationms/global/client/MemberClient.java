package com.a303.notificationms.global.client;

import com.a303.notificationms.domain.member.dto.response.MemberBaseRes;
import com.a303.notificationms.global.api.response.BaseResponse;
import java.util.List;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "memberms")
public interface MemberClient {

	@GetMapping("api/members/v1/{memberId}")
	BaseResponse<MemberBaseRes> getMemberDtoByMemberId(@PathVariable int memberId);

	@GetMapping("api/members/v0/id-list")
	BaseResponse<List<Integer>> getMemberIdList();

}
