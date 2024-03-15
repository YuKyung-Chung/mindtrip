package com.a303.postitms.domain.postitTopic.dto.reponse;

import com.a303.postitms.domain.postit.dto.reponse.PostitTopicListRes;
import java.util.List;

public record PostitTopicRes(
    String topic,
    List<PostitTopicListRes> postitTopicListResList
) {

}
