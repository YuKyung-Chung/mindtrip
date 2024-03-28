package com.a303.consultms.domain.consult.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Builder;

public record ConsultCloseReq(
    boolean isShared
) {

}
