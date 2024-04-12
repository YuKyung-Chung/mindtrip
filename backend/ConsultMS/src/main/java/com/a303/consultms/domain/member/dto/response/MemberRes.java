package com.a303.consultms.domain.member.dto.response;

import lombok.Builder;

@Builder
public record MemberRes(
    int memberId,
    String nickname
) {

}
