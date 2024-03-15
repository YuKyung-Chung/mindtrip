package com.a303.postitms.domain.postitTopic.repository;

import static org.springframework.data.mongodb.core.query.Criteria.where;

import com.a303.postitms.domain.postitTopic.PostitTopic;
import com.a303.postitms.domain.postitTopic.dto.reponse.PostitTopicRes;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.Date;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

@Repository
@AllArgsConstructor
public class PostitTopicRepositoryImpl implements PostitTopicCustomRepository {
}
