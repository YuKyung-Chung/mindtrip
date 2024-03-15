package com.a303.postitms.domain.postit.dto.reponse;

import lombok.Builder;

@Builder
public record PostitTopicListRes(

    String content,

    int reportCount,

    int likeCount
) {

}
