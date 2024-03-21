package com.a303.postitms.domain.postitTopic.repository;

import com.a303.postitms.domain.postitTopic.PostitTopic;
import java.util.Date;
import java.util.List;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface PostitTopicRepository extends MongoRepository<PostitTopic, String>, PostitTopicCustomRepository {
    PostitTopic getPostitTopicById(String postitTopicId);

    PostitTopic findPostitTopicByPostitDate(String postitDate);
}
