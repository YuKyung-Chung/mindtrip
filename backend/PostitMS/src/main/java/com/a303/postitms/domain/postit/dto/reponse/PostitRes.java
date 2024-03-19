package com.a303.postitms.domain.postit.dto.reponse;

import lombok.Builder;

@Builder
public record PostitRes(

    String postitId,
    String content,

    int reportCount,

    int likeCount
) {

}
