package com.a303.postitms.domain.postit.service;

import com.a303.postitms.domain.postit.dto.request.PostitRegistReq;

public interface PostitService {

    String registerPostit(PostitRegistReq postitRegistReq, int memberId);

    void deletePostit(String postitId, int memberId);
}
