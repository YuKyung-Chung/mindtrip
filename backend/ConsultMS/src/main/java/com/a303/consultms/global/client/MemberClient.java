package com.a303.consultms.global.client;

import com.a303.consultms.domain.member.dto.response.MemberBaseRes;
import com.a303.consultms.global.api.response.BaseResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name="memberms")
public interface MemberClient {

    @GetMapping("api/members/v1/{memberId}")
    BaseResponse<MemberBaseRes> getMember(@PathVariable int memberId);

}