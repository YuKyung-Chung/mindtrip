package com.a303.postitms.domain.postitTopic.repository;

import com.a303.postitms.domain.postitTopic.PostitTopic;
import com.a303.postitms.domain.postitTopic.dto.reponse.PostitTopicRes;
import java.sql.Date;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface PostitTopicRepository extends MongoRepository<PostitTopic, String>, PostitTopicCustomRepository {
    PostitTopic getPostitTopicById(String postitTopicId);
}
