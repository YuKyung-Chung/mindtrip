package com.a303.postitms.domain.postit.service;

import com.a303.postitms.domain.postit.dto.reponse.PostitTopicListRes;
import com.a303.postitms.domain.postit.dto.request.PostitRegistReq;
import java.util.Date;

public interface PostitService {


    PostitTopicListRes readPostitList(String date, String order, int village);

    String registerPostit(PostitRegistReq postitRegistReq, int memberId);

    void deletePostit(String postitId, String today, int memberId);
}
