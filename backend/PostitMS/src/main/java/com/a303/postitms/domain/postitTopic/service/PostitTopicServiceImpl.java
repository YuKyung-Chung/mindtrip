package com.a303.postitms.domain.postitTopic.service;

import com.a303.postitms.domain.postitTopic.PostitTopic;
import com.a303.postitms.domain.postitTopic.repository.PostitTopicRepository;
import com.a303.postitms.global.exception.BaseExceptionHandler;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
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
    public void createPoistitTopic() throws BaseExceptionHandler {

        List<PostitTopic> postitTopicList = postitTopicRepository.findAllByOrderByPostitDate();

        String finalDate = postitTopicList.get(postitTopicList.size() - 1).getPostitDate();

        LocalDate nextDate = LocalDate.parse(finalDate, DateTimeFormatter.ISO_DATE).plusDays(1);

        if (LocalDate.now().plusDays(10).isBefore(nextDate)) {
            log.debug("createPoistitTopic method 마지막 일자:{} - 아직 업데이트 되기 전입니다.",
                nextDate);
            return;
        }

        List<PostitTopic> newPostitTopicList = new ArrayList<>();

        for (PostitTopic postitTopic : postitTopicList) {

            String nextDateString = nextDate.format(DateTimeFormatter.ISO_DATE);

            PostitTopic newPostitTopic = PostitTopic.createPostitTopic(postitTopic.getTopic(), nextDateString);

            nextDate = nextDate.plusDays(1);
            newPostitTopicList.add(newPostitTopic);
        }

        postitTopicRepository.saveAll(newPostitTopicList);

        log.debug("createPoistitTopic method : succeed");
    }
}
