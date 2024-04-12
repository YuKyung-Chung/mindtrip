package com.a303.postitms.domain.postit.dto.reponse;

import com.a303.postitms.domain.postit.Postit;
import java.util.List;
import lombok.Builder;

@Builder
public record PostitTopicListRes(
    String topicId,
    String topic,

    String postitDate,

    List<PostitRes> postitResList
) {

}
