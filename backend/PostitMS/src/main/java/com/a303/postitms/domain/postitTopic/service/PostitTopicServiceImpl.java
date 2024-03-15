package com.a303.postitms.domain.postitTopic.service;

import com.a303.postitms.domain.postitTopic.PostitTopic;
import com.a303.postitms.domain.postitTopic.dto.reponse.PostitTopicRes;
import com.a303.postitms.domain.postitTopic.repository.PostitTopicRepository;
import java.util.Date;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Log4j2
@Service
@RequiredArgsConstructor
@Transactional
public class PostitTopicServiceImpl implements PostitTopicService {

    private final PostitTopicRepository postitTopicRepository;

    @Override
    public List<PostitTopic> getPostitTopic() {
        List<PostitTopic> postitTopicRes = postitTopicRepository.findAll();
        return postitTopicRes;
    }
}
