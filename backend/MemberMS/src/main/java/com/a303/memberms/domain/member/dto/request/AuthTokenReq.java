package com.a303.memberms.domain.member.dto.request;

import lombok.Builder;

@Builder
public record AuthTokenReq(
    int memberId,
    String role
) {

}
