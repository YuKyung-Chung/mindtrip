package com.a303.memberms.global.client;

import com.a303.memberms.domain.member.dto.request.AuthTokenReq;
import com.a303.memberms.global.api.response.BaseResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "oauthms")
public interface AuthClient {
    @PostMapping("api/oauthms/v0/token")
    ResponseEntity<BaseResponse<String>> token(
        @RequestBody
        AuthTokenReq authTokenReq
    );
}
