package com.a303.consultms.domain.consult.service;

import com.a303.consultms.domain.consult.Consult;
import com.a303.consultms.domain.consult.repository.ConsultRepository;
import com.a303.consultms.domain.consultLike.LikeConsult;
import com.a303.consultms.domain.consultLike.repository.LikeConsultRepository;
import jakarta.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.redis.core.Cursor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ScanOptions;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Log4j2
public class ConsultScheduler {
    private final RedisTemplate<String, String> redisTemplate;
    private final ConsultRepository consultRepository;
    private final LikeConsultRepository likeConsultRepository;

    @Scheduled(fixedDelay = 1000L * 60L * 60L)
    @Transactional
    public void addLikesToMaria(){
        ScanOptions options = ScanOptions.scanOptions().match("likeConsultId*").build();
        List<LikeConsult> likeConsultList = new ArrayList<>();

        try (Cursor<byte[]> cursor = redisTemplate.getConnectionFactory().getConnection()
            .scan(options)) {
            while (cursor.hasNext()) {
                String redisKey = new String(cursor.next());
                String consultId = redisKey.split("::")[1];

                // consultId에 대한 해시의 모든 필드(field)와 값을 가져옴
                Map<Object, Object> fieldValues = redisTemplate.opsForHash().entries(redisKey);

                Consult consult = consultRepository.findById(Integer.parseInt(consultId)).get();

                if (consult != null) {
                    int likeCount = consult.getLikeCount();

                    for (Object key : fieldValues.keySet()) {
                        String memberId = (String) key;

                        // 이미 MongoDB에 해당 좋아요 정보가 있는지 확인
                        if (likeConsultRepository.findByConsultIdAndMemberId(Integer.parseInt(consultId),
                            Integer.parseInt(memberId)) == null) {
                            LikeConsult likeConsult = LikeConsult.createConsultLike(Integer.parseInt(consultId),
                                Integer.parseInt(memberId));
                            likeConsultList.add(likeConsult);
                            likeCount++;
                        }
                    }
                    consult.setLikeCount(likeCount);
                    consultRepository.save(consult);
                }
            }
            if (!likeConsultList.isEmpty()) {
                likeConsultRepository.saveAll(likeConsultList);
            }
            log.debug("addLikesToMaria schedule method success : {}", likeConsultList.size());
        }
    }
}
