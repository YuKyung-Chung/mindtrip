package com.a303.postitms.domain.postit.repository;

import com.a303.postitms.domain.postit.Postit;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface PostitRepository extends MongoRepository<Postit, String> {
    Postit findPostitById(String postitId);
}
