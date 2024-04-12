package com.a303.postitms.domain.postit.repository;

import com.a303.postitms.domain.postit.Postit;
import com.a303.postitms.domain.postit.dto.reponse.MyPostitRes;
import com.a303.postitms.domain.postit.dto.reponse.PostitRes;
import java.util.List;
import org.springframework.data.domain.Pageable;

public interface PostitCustomRepository {

    Postit findByPostitTopicId(String postitTopicId, int memberId);

    List<Postit> findByPostitTopicIdAndVillageOrder(String postitTopicId, String order, String village, Pageable pageable);

    List<Postit> findByMemberId(int memberId);
}
