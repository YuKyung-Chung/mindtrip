package com.a303.postitms.domain.postitTopic.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Log4j2
public class PostitTopicScheduler {

    private final PostitTopicService postitTopicService;

    @Scheduled(cron = "0 0 12 5 * *")
    @Transactional
    public void createPostitTopic() {
        log.debug("createPoistitTopic scheduler accepted.");
        postitTopicService.createPoistitTopic();
        log.debug("createPoistitTopic scheduler succeed.");
    }

}
