package com.a303.postitms.domain.postitTopic.dto.reponse;

import com.a303.postitms.domain.postit.dto.reponse.PostitRes;
import java.util.List;

public record PostitTopicRes(
    String topic,
    List<PostitRes> postitResList
) {

}
