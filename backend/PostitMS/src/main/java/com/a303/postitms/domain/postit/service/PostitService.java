package com.a303.postitms.domain.postit.service;

import com.a303.postitms.domain.postit.Postit;
import com.a303.postitms.domain.postit.dto.reponse.MyPostitRes;
import com.a303.postitms.domain.postit.dto.reponse.PostitTopicListRes;
import com.a303.postitms.domain.postit.dto.request.PostitRegistReq;
import com.a303.postitms.domain.postitTopic.service.PostitTopicService;
import com.a303.postitms.global.exception.BaseExceptionHandler;
import java.util.Date;
import java.util.List;
import org.springframework.data.domain.Pageable;

public interface PostitService {


    PostitTopicListRes readPostitList(String date, String order, int village, Pageable pageable) throws BaseExceptionHandler;

    List<MyPostitRes> readMyPostitList(int memberId) throws BaseExceptionHandler ;

    String registerPostit(PostitRegistReq postitRegistReq, int memberId) throws BaseExceptionHandler ;

    void deletePostit(String postitId, String today, int memberId) throws BaseExceptionHandler ;
}
