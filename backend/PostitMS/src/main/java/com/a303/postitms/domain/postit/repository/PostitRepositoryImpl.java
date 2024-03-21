package com.a303.postitms.domain.postit.repository;

import static org.springframework.data.mongodb.core.aggregation.Aggregation.match;

import com.a303.postitms.domain.postit.Postit;
import com.a303.postitms.domain.postit.dto.reponse.PostitRes;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.aggregation.AggregationResults;
import org.springframework.data.mongodb.core.aggregation.MatchOperation;
import org.springframework.data.mongodb.core.aggregation.ProjectionOperation;
import org.springframework.data.mongodb.core.aggregation.SortOperation;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

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
    public List<PostitRes> findByPostitTopicIdAndVillageOrder(String postitTopicId, String order, int village) {

        // $match 스테이지: 필요한 조건으로 데이터 필터링
        Criteria criteria = Criteria.where("postit_topic.$id").is(new ObjectId(postitTopicId));
        if (village != 0) {
            criteria.and("village").is(village);
        }

        MatchOperation matchOperation = Aggregation.match(criteria);

        // $project 스테이지: 필요한 필드로 데이터 구조 변환
        ProjectionOperation projectionOperation = Aggregation.project()
            .andExpression("_id").as("id")
            .andExpression("content").as("content")
            .andExpression("report_count").as("reportCount")
            .andExpression("like_count").as("likeCount")
            .andExpression("village").as("village")
            .andExpression("create_time").as("createTime");

        SortOperation sortOperation;

        if(order.equals("like")) {
            sortOperation = Aggregation.sort(
                Sort.by(Sort.Direction.DESC, "likeCount", "createTime")
            );
        } else {
            sortOperation = Aggregation.sort(
                Sort.by(Sort.Direction.DESC, "createTime")
            );
        }

        // Aggregation 파이프라인 생성
        Aggregation aggregation = Aggregation.newAggregation(matchOperation, projectionOperation, sortOperation);

        // Aggregation 실행 및 결과 매핑
        AggregationResults<PostitRes> results = mongoTemplate.aggregate(aggregation, "postit", PostitRes.class);
        List<PostitRes> postitResList = results.getMappedResults();

        return postitResList;
    }

}
