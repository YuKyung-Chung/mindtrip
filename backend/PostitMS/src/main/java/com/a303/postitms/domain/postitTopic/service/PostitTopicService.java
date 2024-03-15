package com.a303.postitms.domain.postitTopic.service;

import com.a303.postitms.domain.postitTopic.PostitTopic;
import com.a303.postitms.domain.postitTopic.dto.reponse.PostitTopicRes;
import java.util.List;

public interface PostitTopicService {

    List<PostitTopic> getPostitTopic();
}
