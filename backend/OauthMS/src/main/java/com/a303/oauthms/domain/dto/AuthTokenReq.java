package com.a303.oauthms.domain.dto;

import lombok.Builder;

@Builder
public record AuthTokenReq(
    int memberId,
    String role
) {

}
