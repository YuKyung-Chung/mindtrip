package com.a303.oauthms.token;


import com.a303.oauthms.domain.dto.JwtAuthToken;
import java.nio.charset.StandardCharsets;
import java.util.Date;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;


import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;

public class AuthTokenProviderImpl implements AuthTokenProvider {
	private final SecretKey key;
	private final long accessTokenLifetimeSeconds;

	public AuthTokenProviderImpl(String secret, long accessTokenLifetime) {
		this.key = new SecretKeySpec(
			secret.getBytes(StandardCharsets.UTF_8),
			"HmacSha256"
		);

		this.accessTokenLifetimeSeconds = accessTokenLifetime;
	}

	@Override
	public String createToken(int memberId, String role) {
		Date currentDate = new Date();
		Date expiration = new Date(currentDate.getTime() + (accessTokenLifetimeSeconds * 1000L));

		return Jwts.builder()
			.header()
				.add("typ", "JWT")
			.and()
			.claim("userId", Integer.toString(memberId))
			.claim("role", role)
			.issuedAt(currentDate)
			.expiration(expiration)
			.signWith(key, Jwts.SIG.HS256)
			.encodePayload(true)
			.compact();
	}

	@Override
	public boolean validate(JwtAuthToken token) throws ExpiredJwtException {
		// token의 claim을 얻는 과정에서 예외 발생 ≡ token이 유효하지 않다
		Claims claims = token.getClaims(this.key);

		return claims != null;
	}

}
