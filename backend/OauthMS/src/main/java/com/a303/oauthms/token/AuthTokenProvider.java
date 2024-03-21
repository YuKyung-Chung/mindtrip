package com.a303.oauthms.token;

import com.a303.oauthms.domain.dto.JwtAuthToken;
import io.jsonwebtoken.ExpiredJwtException;

// 토큰 검증, Authentication 객체 생성
public interface AuthTokenProvider {

	String createToken(int memberId, String role);

	boolean validate(JwtAuthToken token) throws ExpiredJwtException;
}
