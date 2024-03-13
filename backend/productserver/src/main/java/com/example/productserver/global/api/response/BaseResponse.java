package com.example.productserver.global.api.response;

import com.example.productserver.global.exception.code.SuccessCode;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.ResponseEntity;

@Getter
@AllArgsConstructor
public class BaseResponse<T> {
    private T result;
    private int status;
    private String message;

    public static <T> ResponseEntity<BaseResponse<T>> success(SuccessCode successCode, T data) {
        return ResponseEntity
                .status(successCode.getStatus())
                .body(new BaseResponse<>(
                        data,
                        successCode.getStatus(),
                        successCode.getMessage()
                ));
    }
}

