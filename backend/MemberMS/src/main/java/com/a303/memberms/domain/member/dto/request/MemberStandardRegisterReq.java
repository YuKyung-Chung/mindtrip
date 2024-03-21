package com.a303.memberms.domain.member.dto.request;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;


public record MemberStandardRegisterReq (
    String id,
    String password,
    String nickname
) {

}