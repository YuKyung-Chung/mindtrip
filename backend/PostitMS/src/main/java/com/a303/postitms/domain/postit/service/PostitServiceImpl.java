package com.a303.postitms.domain.postit.service;

import com.a303.postitms.domain.postit.Postit;
import com.a303.postitms.domain.postit.dto.reponse.PostitRes;
import com.a303.postitms.domain.postit.dto.request.PostitModifyReq;
import com.a303.postitms.domain.postit.dto.request.PostitRegistReq;
import com.a303.postitms.domain.postit.repository.PostitRepository;
import com.a303.postitms.domain.postitTopic.PostitTopic;
import com.a303.postitms.domain.postitTopic.repository.PostitTopicRepository;
import com.a303.postitms.global.api.response.BaseResponse;
import com.a303.postitms.global.client.MemberClient;
import com.a303.postitms.global.client.dto.Role;
import com.a303.postitms.global.client.dto.response.MemberBaseRes;
import com.a303.postitms.global.exception.BaseExceptionHandler;
import com.a303.postitms.global.exception.code.ErrorCode;
import lombok.AllArgsConstructor;
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
    @Transactional
    public String registerPostit(PostitRegistReq postitRegistReq, int memberId) {

        BaseResponse<MemberBaseRes> memberRes = memberClient.getMember(memberId);

        if(memberRes == null ) {
            throw new BaseExceptionHandler("회원가입한 유저만 등록 가능합니다.", ErrorCode.FORBIDDEN_ERROR);
        }

        PostitTopic postitTopic = postitTopicRepository.getPostitTopicById(
            postitRegistReq.topicId());

        Postit postit = Postit.createPostit(postitRegistReq.content(), memberId,
            postitTopic.getTopic());

        postitRepository.save(postit);

        return postit.getId();
    }

    @Override
    @Transactional
    public void deletePostit(String postitId, int memberId) {
        Postit postit = postitRepository.findPostitById(postitId);

        if (postit == null) {
            throw new BaseExceptionHandler("존재하지 않는 포스트잇입니다.", ErrorCode.NOT_FOUND_ERROR);
        }

        BaseResponse<MemberBaseRes> memberRes = memberClient.getMember(memberId);

        if(memberRes == null || (memberRes.getResult().role().equals(Role.USER) && postit.getMemberId() != memberId)) {
            throw new BaseExceptionHandler("작성한 사용자만 삭제 가능합니다", ErrorCode.FORBIDDEN_ERROR);
        }

        postitRepository.delete(postit);
    }
}
