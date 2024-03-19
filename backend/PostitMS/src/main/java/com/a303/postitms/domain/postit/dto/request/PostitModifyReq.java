package com.a303.postitms.domain.postit.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;

@Builder
public record PostitModifyReq(

    @NotNull
    String postitId,

    @NotBlank(message = "제목은 필수 입니다.")
    String content

) {

}
