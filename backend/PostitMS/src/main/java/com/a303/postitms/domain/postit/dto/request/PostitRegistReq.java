package com.a303.postitms.domain.postit.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.util.Date;
import lombok.Builder;
import org.springframework.format.annotation.DateTimeFormat;

@Builder
public record PostitRegistReq(

    @NotNull
    String topicId,

    @NotBlank(message = "내용은 필수 입니다.")
    String content,

    @NotBlank(message = "날짜는 필수 입니다.")
    String postitDate

) {

}
