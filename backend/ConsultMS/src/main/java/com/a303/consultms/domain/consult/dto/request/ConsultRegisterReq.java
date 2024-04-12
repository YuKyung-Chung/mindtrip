package com.a303.consultms.domain.consult.dto.request;

import jakarta.validation.constraints.NotBlank;

public record ConsultRegisterReq(
    @NotBlank(message = "제목은 필수입니다.")
    String title,

    String content,

    int categoryId
) {

}
