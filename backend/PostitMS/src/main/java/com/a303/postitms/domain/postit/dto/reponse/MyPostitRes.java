package com.a303.postitms.domain.postit.dto.reponse;

import com.a303.postitms.domain.postitTopic.dto.reponse.PostitTopicRes;
import lombok.Builder;

@Builder
public record MyPostitRes(

    String id,

    PostitTopicRes postitTopicRes,
    String content,

    int reportCount,

    int likeCount,
    String village
) {

}
