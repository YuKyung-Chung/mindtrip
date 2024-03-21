package com.a303.postitms.domain.postit.service;

import com.a303.postitms.domain.postit.Postit;
import com.a303.postitms.domain.postit.dto.reponse.PostitRes;
import com.a303.postitms.domain.postit.dto.reponse.PostitTopicListRes;
import com.a303.postitms.domain.postit.dto.request.PostitRegistReq;
import com.a303.postitms.domain.postit.repository.PostitRepository;
import com.a303.postitms.domain.postitTopic.PostitTopic;
import com.a303.postitms.domain.postitTopic.repository.PostitTopicRepository;
import com.a303.postitms.global.api.response.BaseResponse;
import com.a303.postitms.global.client.MemberClient;
import com.a303.postitms.domain.member.Role;
import com.a303.postitms.domain.member.dto.response.MemberBaseRes;
import com.a303.postitms.global.exception.BaseExceptionHandler;
import com.a303.postitms.global.exception.code.ErrorCode;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Log4j2
@Service
@RequiredArgsConstructor
public class PostitServiceImpl implements PostitService {

    private final PostitTopicRepository postitTopicRepository;
    private final PostitRepository postitRepository;
    private final MemberClient memberClient;

    @Override
    public PostitTopicListRes readPostitList(String date, String order, int village) {

        PostitTopic postitTopic = postitTopicRepository.findPostitTopicByPostitDate(date);

        if (postitTopic == null) {
            log.error("readPostitList method Date:{}에 해당하는 Topic이 없습니다.",
                date);

            throw new BaseExceptionHandler(ErrorCode.NOT_FOUND_POSTIT_TOPIC_EXCEPTION);
        }

        List<PostitRes> postitResList = postitRepository.findByPostitTopicIdAndVillageOrder(
            postitTopic.getId(),
            order, village);

        return PostitTopicListRes.builder()
            .topicId(postitTopic.getId())
            .topic(postitTopic.getTopic())
            .postitDate(postitTopic.getPostitDate())
            .postitResList(postitResList)
            .build();
    }

    @Override
    @Transactional
    public String registerPostit(PostitRegistReq postitRegistReq, int memberId) {

        BaseResponse<MemberBaseRes> memberRes = memberClient.getMember(memberId);

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
            memberRes.getResult().villageId());

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
    public void deletePostit(String postitId, String date, int memberId) {
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
}
