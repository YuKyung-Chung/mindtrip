package com.a303.consultms.domain.consult.dto.request;

import jakarta.validation.constraints.NotBlank;

public record ConsultCloseReq(
    int consultId
) {

}
