package com.a303.oauthms.controller;

import com.a303.oauthms.domain.dto.AuthTokenReq;
import com.a303.oauthms.domain.dto.JwtAuthToken;
import com.a303.oauthms.global.api.response.BaseResponse;
import com.a303.oauthms.global.exception.code.SuccessCode;
import com.a303.oauthms.token.AuthTokenProvider;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/*
public class BaseResponse<T> {
    private T result;
    private int status;
    private String message;
 */

@RestController
@CrossOrigin("*")
@RequestMapping("/api/oauthms")
@RequiredArgsConstructor
public class AuthController {
    private final AuthTokenProvider tokenProvider;

    @PostMapping("/v0/token")
    public ResponseEntity<BaseResponse<String>> token(
        @RequestBody
        AuthTokenReq authTokenReq
    ) {
        String token = tokenProvider.createToken(
            authTokenReq.memberId(),
            authTokenReq.role()
        );

//        response.setHeader("Authorization", token);

        ResponseEntity<BaseResponse<String>> response = BaseResponse.success(
            SuccessCode.TOKEN_CREATION_SUCCESS,
            token
        );
//        HttpHeaders headers = response.getHeaders();
//        HttpHeaders headers = new HttpHeaders();
//        HttpHeaders headers1 = response.getHeaders();
//        for (String key : headers1.keySet()) {
//            headers.set(key, headers1.get(key).get(0));
//        }
//        headers.setBearerAuth(token);
//
//        return response;

        return response;
    }
}
