package com.a303.postitms.domain.postit.dto.reponse;

import jakarta.persistence.Id;
import java.util.Date;
import lombok.Builder;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.format.annotation.DateTimeFormat;

@Builder
public record PostitRes(

    String id,
    String content,

    int reportCount,

    int likeCount,
    int village
) {

}
