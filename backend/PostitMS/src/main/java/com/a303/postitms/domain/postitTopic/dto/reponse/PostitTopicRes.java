package com.a303.postitms.domain.postitTopic.dto.reponse;

import lombok.Builder;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Field;

@Builder
public record PostitTopicRes(
    String id,

    String topic,

    String postitDate
){

}
