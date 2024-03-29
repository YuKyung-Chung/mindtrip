package com.a303.gatewayms.filters;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import io.micrometer.core.instrument.config.validate.Validated.Secret;
import java.nio.charset.StandardCharsets;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

@Component
@Slf4j
public class AuthorizationHeaderFilter extends
    AbstractGatewayFilterFactory<AuthorizationHeaderFilter.Config> {

    @Value("${jwt.secret-key}")
    private String secret;

    Environment env;

    public AuthorizationHeaderFilter() {
        super(Config.class);
    }

    public static class Config {

    }

    @Override
    public GatewayFilter apply(AuthorizationHeaderFilter.Config config) {
        return (exchange, chain) -> {

            ServerHttpRequest request = exchange.getRequest();
            log.info("요청한 uri : " + request.getURI());

            // Authorization 헤더 없다면 에러
            if (!request.getHeaders().containsKey(HttpHeaders.AUTHORIZATION)) {
                return onError(exchange, "No authorization header", HttpStatus.UNAUTHORIZED);
            }

            // "Authorization" 헤더에서 JWT 토큰을 추출.
            String authorizationHeader = request.getHeaders().get(HttpHeaders.AUTHORIZATION).get(0);
            String jwt = authorizationHeader.replace("Bearer", "").trim();

            Claims claims = isJwtValid(jwt);

            // 추출한 JWT 토큰의 유효성을 확인
            if (claims == null) {
                return onError(exchange, "JWT Token is not valid", HttpStatus.UNAUTHORIZED);
            }

            log.info("[GatewayFilter] JWT VALID");

            int memberId = Integer.parseInt(claims.get("userId", String.class));

            // 멤버 ID를 다른 서비스로 전달
            ServerHttpRequest mutatedRequest = exchange.getRequest().mutate()
                .header("x-member-id", String.valueOf(memberId))
                .build();
            ServerWebExchange mutatedExchange = exchange.mutate()
                .request(mutatedRequest)
                .build();

            return chain.filter(mutatedExchange);
        };
    }

    // Mono, Flux -> Spring WebFlux (기존의 SpringMVC 방식이 아니기때문에 Servlet 을 사용하지 않음)
    private Mono<Void> onError(ServerWebExchange exchange, String err, HttpStatus httpStatus) {
        ServerHttpResponse response = exchange.getResponse();
        response.setStatusCode(httpStatus);

        log.error("[GatewayFilter onError method] : {} ", err);
        return response.setComplete();
    }

    private Claims isJwtValid(String jwt) {

        Claims claims = null;

        try {
            SecretKey key = new SecretKeySpec(
                secret.getBytes(StandardCharsets.UTF_8),
                "HmacSha256"
            );

            claims = Jwts.parser()
                .verifyWith(key)
                .build()
                .parseSignedClaims(jwt)
                .getPayload();

        } catch (Exception e) {
            log.error("[isJwtValid method] Token is not valid. : {} \n {}", jwt, e.getMessage());
            return null;
        }

        // 'subject' 값이 비어있으면 JWT가 유효하지 않다고 판단하고 반환값을 false로 변경
        if (claims == null || claims.isEmpty()) {
            log.error("[isJwtValid method] claims is not found");
            return null;
        }

        // 최종적으로 JWT의 유효성 여부를 나타내는 반환값을 반환
        return claims;
    }
}
