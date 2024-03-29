package com.a303.postitms.domain.postit.service;

import com.a303.postitms.domain.likePostit.LikePostit;
import com.a303.postitms.domain.likePostit.repository.LikePostitRepository;
import com.a303.postitms.domain.postit.Postit;
import com.a303.postitms.domain.postit.dto.reponse.MyPostitRes;
import com.a303.postitms.domain.postit.dto.reponse.PostitRes;
import com.a303.postitms.domain.postit.dto.reponse.PostitTopicListRes;
import com.a303.postitms.domain.postit.dto.request.PostitRegistReq;
import com.a303.postitms.domain.postit.repository.PostitRepository;
import com.a303.postitms.domain.postitTopic.PostitTopic;
import com.a303.postitms.domain.postitTopic.dto.reponse.PostitTopicRes;
import com.a303.postitms.domain.postitTopic.repository.PostitTopicRepository;
import com.a303.postitms.domain.reportPostit.ReportPostit;
import com.a303.postitms.domain.reportPostit.repository.ReportPostitRepository;
import com.a303.postitms.global.api.response.BaseResponse;
import com.a303.postitms.global.client.MemberClient;
import com.a303.postitms.domain.member.Role;
import com.a303.postitms.domain.member.dto.response.MemberBaseRes;
import com.a303.postitms.global.exception.BaseExceptionHandler;
import com.a303.postitms.global.exception.code.ErrorCode;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.domain.Pageable;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Log4j2
@Service
@RequiredArgsConstructor
public class PostitServiceImpl implements PostitService {

    private final PostitTopicRepository postitTopicRepository;
    private final PostitRepository postitRepository;
    private final MemberClient memberClient;
    private final RedisTemplate<String, String> redisTemplate;
    private final LikePostitRepository likePostitRepository;
    private final ReportPostitRepository reportPostitRepository;

    @Override
    public PostitTopicListRes readPostitList(String date, String order, String village,
        Pageable pageable, int memberId) throws BaseExceptionHandler {

        PostitTopic postitTopic = postitTopicRepository.findPostitTopicByPostitDate(date);

        if (postitTopic == null) {
            log.error("readPostitList method Date:{}에 해당하는 Topic이 없습니다.",
                date);

            throw new BaseExceptionHandler(ErrorCode.NOT_FOUND_POSTIT_TOPIC_EXCEPTION);
        }

        List<Postit> postitList = postitRepository.findByPostitTopicIdAndVillageOrder(
            postitTopic.getId(),
            order, village, pageable);

        List<PostitRes> postitResList = new ArrayList<>();

        for (Postit postit : postitList) {

            PostitRes postitRes = PostitRes.builder()
                .id(postit.getId())
                .content(postit.getContent())
                .reportCount(postit.getReportCount())
                .likeCount(postit.getLikeCount())
                .village(postit.getVillage())
                .isLike(isLike(postit.getId(), memberId))
                .isReport(isReport(postit.getId(), memberId))
                .build();

            postitResList.add(postitRes);
        }

        return PostitTopicListRes.builder()
            .topicId(postitTopic.getId())
            .topic(postitTopic.getTopic())
            .postitDate(postitTopic.getPostitDate())
            .postitResList(postitResList)
            .build();
    }

    @Override
    public List<MyPostitRes> readMyPostitList(int memberId) throws BaseExceptionHandler {

        List<Postit> postitResList = postitRepository.findByMemberId(memberId);

        List<MyPostitRes> myPostitResList = postitResList.stream()
            .map(postit -> MyPostitRes.builder()
                .id(postit.getId())
                .postitTopicRes(PostitTopicRes.builder()
                    .id(postit.getPostitTopic().getId())
                    .topic(postit.getPostitTopic().getTopic())
                    .postitDate(postit.getPostitTopic().getPostitDate()).build())
                .content(postit.getContent())
                .reportCount(postit.getReportCount())
                .likeCount(postit.getLikeCount())
                .village(postit.getVillage())
                .build()
            ).collect(Collectors.toList());

        return myPostitResList;
    }


    @Override
    @Transactional
    public String registerPostit(PostitRegistReq postitRegistReq, int memberId)
        throws BaseExceptionHandler {

        PostitTopic postitTopic = postitTopicRepository.getPostitTopicById(
            postitRegistReq.topicId());

        if (postitTopic == null) {
            log.error("registerPostit method PostitTopic:{}이 없습니다.",
                postitRegistReq.topicId());

            throw new BaseExceptionHandler(ErrorCode.NOT_FOUND_POSTIT_TOPIC_EXCEPTION);
        }

        if (!postitTopic.getPostitDate().equals(postitRegistReq.postitDate())) {
            log.error(
                "registerPostit method : postitTopic.postitDate(): {}와 postitRegistReq.postitDate() : {} 이 다릅니다.",
                postitTopic.getPostitDate(), postitRegistReq.postitDate());

            throw new BaseExceptionHandler(ErrorCode.FORBIDDEN_ERROR_DAY);
        }

        Postit postit = Postit.createPostit(postitRegistReq.content(), postitTopic, memberId,
            postitRegistReq.village());

        if (postitRepository.findByPostitTopicId(postitTopic.getId(), memberId) != null) {

            log.error("registerPostit method postit:{}이 이미 존재합니다.",
                postit.getId());

            throw new BaseExceptionHandler(ErrorCode.POSTIT_ALREADY_EXISTS);
        }

        postitRepository.save(postit);

        log.debug("registerPostit method postitId:{} success",
            postit.getId());

        return postit.getId();
    }

    @Override
    @Transactional
    public void deletePostit(String postitId, String date, int memberId)
        throws BaseExceptionHandler {
        Postit postit = postitRepository.findPostitById(postitId);

        if (postit == null) {
            log.error("deletePostit method postitId:{} 가 존재하지 않습니다.",
                postitId);

            throw new BaseExceptionHandler("존재하지 않는 포스트잇입니다.", ErrorCode.NOT_FOUND_ERROR);
        }

        BaseResponse<MemberBaseRes> memberRes = memberClient.getMember(memberId);

        if (memberRes == null || (memberRes.getResult().role().equals(Role.USER)
            && postit.getMemberId() != memberId)) {

            log.error("deletePostit method member:{} 에게 삭제 권한이 없습니다.",
                memberId);

            throw new BaseExceptionHandler(ErrorCode.FORBIDDEN_ERROR_CREATOR);
        }

        if (!postit.getPostitTopic().getPostitDate().equals(date)) {
            log.error("deletePostit method postit Topic day:{}, today : {} 가 일치하지 않습니다.",
                postit.getPostitTopic().getPostitDate(), date);

            throw new BaseExceptionHandler(ErrorCode.FORBIDDEN_ERROR_DAY);
        }

        postitRepository.delete(postit);

        log.debug("deletePostit method postitId:{} success", postitId);
    }

    @Transactional
    @Override
    public void addLikesToRedis(String postitId, int memberId) throws BaseExceptionHandler {

        HashOperations<String, String, String> hashOperations = redisTemplate.opsForHash();
        String key = "likePostitId::" + postitId;
        String field = String.valueOf(memberId);

        if (hashOperations.get(key, field) == null) {
            LikePostit likePostit = likePostitRepository.findByPostitIdAndMemberId(postitId,
                memberId);

            if (likePostit != null) {
                log.error("addLikesToRedis method postitId: {}, memberId: {}, POSTIT_ALREADY_LIKE",
                    postitId, memberId);
                throw new BaseExceptionHandler(ErrorCode.POSTIT_ALREADY_LIKE);
            }
            hashOperations.put(key, field, String.valueOf(1));
        } else {
            log.error("addLikesToRedis method postitId: {}, memberId: {}, POSTIT_ALREADY_LIKE",
                postitId, memberId);
            throw new BaseExceptionHandler(ErrorCode.POSTIT_ALREADY_LIKE);
        }

        log.debug("addLikesToRedis method postitId: {} memberId:{} success ", postitId, memberId);
    }

    @Transactional
    @Override
    public void deleteLikePostit(String postitId, int memberId) throws BaseExceptionHandler {

        HashOperations<String, String, String> hashOperations = redisTemplate.opsForHash();

        String key = "likePostitId::" + postitId;
        String field = String.valueOf(memberId);

        if (hashOperations.get(key, field) == null) {
            LikePostit likePostit = likePostitRepository.findByPostitIdAndMemberId(postitId,
                memberId);

            if (likePostit == null) {
                log.error(
                    "deleteLikePostits method postitId: {}, memberId: {}, NOT_FOUND_POSTIT_LIKE",
                    postitId, memberId);
                throw new BaseExceptionHandler(ErrorCode.NOT_FOUND_POSTIT_LIKE);
            } else {
                likePostitRepository.delete(likePostit);
                Postit postit = postitRepository.findPostitById(postitId);
                int cnt = postit.getLikeCount() - 1;
                postit.setLikeCount(Math.max(cnt, 0));
                postitRepository.save(postit);
            }
        } else {
            hashOperations.delete(key, field);
        }

        log.debug("deleteLikePostits method postitId: {} memberId:{} success ", postitId, memberId);
    }

    @Transactional
    @Override
    public void addReportToRedis(String postitId, int memberId) throws BaseExceptionHandler {

        HashOperations<String, String, String> hashOperations = redisTemplate.opsForHash();
        String key = "reportPostitId::" + postitId;
        String field = String.valueOf(memberId);

        if (hashOperations.get(key, field) == null) {
            ReportPostit reportPostit = reportPostitRepository.findByPostitIdAndMemberId(postitId,
                memberId);

            if (reportPostit != null) {
                log.error(
                    "addReportToRedis method postitId: {}, memberId: {}, POSTIT_ALREADY_REPORT",
                    postitId, memberId);
                throw new BaseExceptionHandler(ErrorCode.POSTIT_ALREADY_REPORT);
            }
            hashOperations.put(key, field, String.valueOf(1));
        } else {
            log.error("addReportToRedis method postitId: {}, memberId: {}, POSTIT_ALREADY_REPORT",
                postitId, memberId);
            throw new BaseExceptionHandler(ErrorCode.POSTIT_ALREADY_REPORT);
        }

        log.debug("addReportToRedis method postitId: {} memberId:{} success ", postitId, memberId);
    }

    @Transactional
    @Override
    public void deleteReportPostit(String postitId, int memberId) throws BaseExceptionHandler {

        HashOperations<String, String, String> hashOperations = redisTemplate.opsForHash();

        String key = "reportPostitId::" + postitId;
        String field = String.valueOf(memberId);

        if (hashOperations.get(key, field) == null) {
            ReportPostit reportPostit = reportPostitRepository.findByPostitIdAndMemberId(postitId,
                memberId);

            if (reportPostit == null) {
                log.error(
                    "deleteReportPostits method postitId: {}, memberId: {}, NOT_FOUND_POSTIT_REPORT",
                    postitId, memberId);
                throw new BaseExceptionHandler(ErrorCode.NOT_FOUND_POSTIT_REPORT);
            } else {
                reportPostitRepository.delete(reportPostit);
                Postit postit = postitRepository.findPostitById(postitId);
                int cnt = postit.getReportCount() - 1;
                postit.setReportCount(Math.max(cnt, 0));
                postitRepository.save(postit);
            }
        } else {
            hashOperations.delete(key, field);
        }

        log.debug("deleteReportPostits method postitId: {} memberId:{} success ", postitId,
            memberId);
    }

    private boolean isLike(String postitId, int memberId) {

        HashOperations<String, String, String> hashOperations = redisTemplate.opsForHash();

        String key = "likePostitId::" + postitId;
        String field = String.valueOf(memberId);

        if (hashOperations.get(key, field) == null) {
            return likePostitRepository.findByPostitIdAndMemberId(postitId, memberId) != null;
        } else {
            return true;
        }
    }

    private boolean isReport(String postitId, int memberId) {

        HashOperations<String, String, String> hashOperations = redisTemplate.opsForHash();

        String key = "reportPostitId::" + postitId;
        String field = String.valueOf(memberId);

        if (hashOperations.get(key, field) == null) {
            return reportPostitRepository.findByPostitIdAndMemberId(postitId, memberId) != null;
        } else {
            return true;
        }
    }
}
