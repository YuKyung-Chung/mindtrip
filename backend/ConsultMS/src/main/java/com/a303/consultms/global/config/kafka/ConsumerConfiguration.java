package com.a303.consultms.global.config.kafka;

import com.google.common.collect.ImmutableMap;
import java.util.Map;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.support.serializer.ErrorHandlingDeserializer;
import org.springframework.kafka.support.serializer.JsonDeserializer;

@EnableKafka
@Configuration
@RequiredArgsConstructor
@Slf4j
public class ConsumerConfiguration {
    // KafkaListener 컨테이너 팩토리를 생성하는 Bean 메서드
    @Bean
    ConcurrentKafkaListenerContainerFactory<String, String> kafkaListenerContainerFactory() {
        ConcurrentKafkaListenerContainerFactory<String, String> factory = new ConcurrentKafkaListenerContainerFactory<>();
        factory.setConsumerFactory(consumerFactory());
        return factory;
    }

    // Kafka ConsumerFactory를 생성하는 Bean 메서드
    @Bean
    public ConsumerFactory<String, String> consumerFactory() {
        JsonDeserializer<String> deserializer = new JsonDeserializer<>(String.class);
        deserializer.addTrustedPackages("*");
        // 패키지 신뢰 오류로 인해 모든 패키지를 신뢰하도록 작성
        // ErrorHandlingDeserializer로 감싸기
        ErrorHandlingDeserializer<String> errorHandlingValueDeserializer = new ErrorHandlingDeserializer<>(deserializer);
        // Kafka Consumer 구성을 위한 설정값들을 설정 -> 변하지 않는 값이므로 ImmutableMap을 이용하여 설정
        Map<String, Object> consumerConfigurations =
            ImmutableMap.<String, Object>builder()
                .put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:8000")
                .put(ConsumerConfig.GROUP_ID_CONFIG, KafkaChatConstants.GROUP_ID) // GROUP_ID를 인스턴스마다 구분
                .put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class)
                .put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, errorHandlingValueDeserializer)
                .put(ErrorHandlingDeserializer.VALUE_DESERIALIZER_CLASS, deserializer.getClass().getName())
                .put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "latest")
                .build();

        return new DefaultKafkaConsumerFactory<>(consumerConfigurations, new StringDeserializer(), errorHandlingValueDeserializer);
    }
}
