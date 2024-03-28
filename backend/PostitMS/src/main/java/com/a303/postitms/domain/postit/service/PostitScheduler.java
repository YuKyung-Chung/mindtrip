package com.a303.postitms.domain.postit.service;

import com.a303.postitms.domain.likePostit.LikePostit;
import com.a303.postitms.domain.likePostit.repository.LikePostitRepository;
import com.a303.postitms.domain.postit.Postit;
import com.a303.postitms.domain.postit.repository.PostitRepository;
import com.a303.postitms.domain.reportPostit.ReportPostit;
import com.a303.postitms.domain.reportPostit.repository.ReportPostitRepository;
import com.a303.postitms.global.exception.BaseExceptionHandler;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.redis.core.Cursor;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ScanOptions;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Log4j2
public class PostitScheduler {

    private final RedisTemplate<String, String> redisTemplate;
    private final PostitRepository postitRepository;
    private final LikePostitRepository likePostitRepository;
    private final ReportPostitRepository reportPostitRepository;

    @Scheduled(fixedDelay = 1000L * 60L * 60L)
    @Transactional
    public void addLikesToMaria() {
        ScanOptions options = ScanOptions.scanOptions().match("likePostitId*").build();
        List<LikePostit> likePostitList = new ArrayList<>();

        try (Cursor<byte[]> cursor = redisTemplate.getConnectionFactory().getConnection()
            .scan(options)) {
            while (cursor.hasNext()) {
                String redisKey = new String(cursor.next());
                String postitId = redisKey.split("::")[1];

                // postitId에 대한 해시의 모든 필드(field)와 값을 가져옴
                Map<Object, Object> fieldValues = redisTemplate.opsForHash().entries(redisKey);

                Postit postit = postitRepository.findPostitById(postitId);

                if (postit != null) {
                    int likeCount = postit.getLikeCount();

                    for (Object key : fieldValues.keySet()) {
                        String memberId = (String) key;

                        // 이미 MongoDB에 해당 좋아요 정보가 있는지 확인
                        if (likePostitRepository.findByPostitIdAndMemberId(postitId,
                            Integer.parseInt(memberId)) == null) {
                            LikePostit likePostit = LikePostit.createLikePostit(postitId,
                                Integer.parseInt(memberId));
                            likePostitList.add(likePostit);
                            likeCount++;
                        }
                    }
                    postit.setLikeCount(likeCount);
                    postitRepository.save(postit);
                }
            }
            if (!likePostitList.isEmpty()) {
                likePostitRepository.saveAll(likePostitList);
            }
            log.debug("addLikesToMaria schedule method success : {}", likePostitList.size());
        }
    }

    @Scheduled(fixedDelay = 1000L * 60L * 60L)
    @Transactional
    public void addReportsToMaria() {
        ScanOptions options = ScanOptions.scanOptions().match("reportPostitId*").build();
        List<ReportPostit> reportPostitList = new ArrayList<>();

        try (Cursor<byte[]> cursor = redisTemplate.getConnectionFactory().getConnection()
            .scan(options)) {
            while (cursor.hasNext()) {
                String redisKey = new String(cursor.next());
                String postitId = redisKey.split("::")[1];

                // postitId에 대한 해시의 모든 필드(field)와 값을 가져옴
                Map<Object, Object> fieldValues = redisTemplate.opsForHash().entries(redisKey);

                Postit postit = postitRepository.findPostitById(postitId);

                if (postit != null) {
                    int reportCount = postit.getReportCount();

                    for (Object key : fieldValues.keySet()) {
                        String memberId = (String) key;

                        // 이미 MongoDB에 해당 신고 정보가 있는지 확인
                        if (reportPostitRepository.findByPostitIdAndMemberId(postitId,
                            Integer.parseInt(memberId)) == null) {
                            ReportPostit reportPostit = ReportPostit.createReportPostit(postitId,
                                Integer.parseInt(memberId));
                            reportPostitList.add(reportPostit);
                            reportCount++;
                        }
                    }
                    postit.setReportCount(reportCount);
                    postitRepository.save(postit);
                }
            }
            if (!reportPostitList.isEmpty()) {
                reportPostitRepository.saveAll(reportPostitList);
            }
            log.debug("addReportsToMaria schedule method success : {}", reportPostitList.size());
        }
    }
}
