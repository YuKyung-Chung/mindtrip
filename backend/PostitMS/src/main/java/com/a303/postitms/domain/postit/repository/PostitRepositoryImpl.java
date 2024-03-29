package com.a303.postitms.domain.postit.repository;

import static org.springframework.data.mongodb.core.aggregation.Aggregation.match;

import com.a303.postitms.domain.postit.Postit;
import com.a303.postitms.domain.postit.dto.reponse.MyPostitRes;
import com.a303.postitms.domain.postit.dto.reponse.PostitRes;
import java.util.Arrays;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.aggregation.AggregationResults;
import org.springframework.data.mongodb.core.aggregation.LookupOperation;
import org.springframework.data.mongodb.core.aggregation.MatchOperation;
import org.springframework.data.mongodb.core.aggregation.ProjectionOperation;
import org.springframework.data.mongodb.core.aggregation.SortOperation;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.redis.core.HashOperations;

import static org.springframework.data.mongodb.core.aggregation.Aggregation.*;

@RequiredArgsConstructor
public class PostitRepositoryImpl implements PostitCustomRepository {

    private final MongoTemplate mongoTemplate;

    @Override
    public Postit findByPostitTopicId(String postitTopicId, int memberId) {

        Query query = new Query(
            Criteria.where("postit_topic.$id").is(new ObjectId(postitTopicId))
                .and("member_id").is(memberId)
        );

        return mongoTemplate.findOne(query, Postit.class);
    }

    @Override
    public List<Postit> findByPostitTopicIdAndVillageOrder(String postitTopicId, String order,
        String village, Pageable pageable) {

        // $match 스테이지: 필요한 조건으로 데이터 필터링
        Criteria criteria = Criteria.where("postit_topic.$id").is(new ObjectId(postitTopicId));

        if ("".equals(village)) {
            criteria.and("village").is(village);
        }
        Query query = new Query(criteria);

        if (order.equals("like")) {
            query.with(Sort.by(Sort.Direction.DESC, "like_count", "create_time"));
        } else {
            query.with(Sort.by(Sort.Direction.DESC, "create_time"));
        }

        query.with(pageable);

        return mongoTemplate.find(query, Postit.class);
    }

    @Override
    public List<Postit> findByMemberId(int memberId) {

        Query query = new Query(
            Criteria.where("member_id").is(memberId)
        );

        query.with(Sort.by(Sort.Direction.DESC, "create_time"));

        return mongoTemplate.find(query, Postit.class);
    }

}
