package com.a303.postitms.domain.postit.repository;

import com.a303.postitms.domain.postit.Postit;
import com.a303.postitms.domain.postit.dto.reponse.PostitRes;
import java.util.List;

public interface PostitCustomRepository {

    Postit findByPostitTopicId(String postitTopicId, int memberId);

    List<PostitRes> findByPostitTopicIdAndVillageOrder(String postitTopicId, String order, int village);
}
