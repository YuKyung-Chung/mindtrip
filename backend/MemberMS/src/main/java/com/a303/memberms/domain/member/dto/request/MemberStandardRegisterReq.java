package com.a303.memberms.domain.member.dto.request;

public record MemberStandardRegisterReq (
    String id,
    String password,
    String nickname
) {

}