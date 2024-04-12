package com.a303.postitms.global.client;

import com.a303.postitms.global.api.response.BaseResponse;
import com.a303.postitms.domain.member.dto.response.MemberBaseRes;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name="memberms")
public interface MemberClient {

    @GetMapping("api/members/v0/{memberId}")
    BaseResponse<MemberBaseRes> getMember(@PathVariable int memberId);

}
