package com.a303.consultms.domain.message.repository;

import com.a303.consultms.domain.message.Message;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface MessageRepository extends MongoRepository<Message, String> {

}
