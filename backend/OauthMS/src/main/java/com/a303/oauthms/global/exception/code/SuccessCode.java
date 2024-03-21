package com.a303.oauthms.global.exception.code;

import lombok.Getter;

@Getter
public enum SuccessCode {
    // 조회 성공 코드 (HTTP Response: 200 OK)
    TOKEN_CREATION_SUCCESS(200, "200", "TOKEN CREATION SUCCESS"),
    TOKEN_VALIDATION_SUCCESS(200, "200", "TOKEN VALIDATION SUCCESS"),
    TOKEN_VALIDATION_FAILURE(400, "400", "TOKEN VALIDATION FAILURE"),


    ; // End

    // 성공 코드의 '코드 상태'를 반환한다.
    private final int status;

    // 성공 코드의 '코드 값'을 반환한다.
    private final String code;

    // 성공 코드의 '코드 메시지'를 반환한다.s
    private final String message;

    // 생성자 구성
    SuccessCode(final int status, final String code, final String message) {
        this.status = status;
        this.code = code;
        this.message = message;
    }
}
