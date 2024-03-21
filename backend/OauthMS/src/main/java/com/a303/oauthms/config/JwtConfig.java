package com.a303.oauthms.config;

import com.a303.oauthms.token.AuthTokenProvider;
import com.a303.oauthms.token.AuthTokenProviderImpl;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class JwtConfig {
    @Value("${jwt.secret-key}")
    private String secretKey;

    @Value("${jwt.access-token.lifetime}")
    private String accessTokenLifetimeSeconds;

//    @Value("${jwt.refresh-token.lifetime}")
//    private String refreshTokenLifetimeSeconds;

    @Bean
    public AuthTokenProvider authTokenProvider() {
        return new AuthTokenProviderImpl(secretKey, Long.parseLong(accessTokenLifetimeSeconds));
    }
}

