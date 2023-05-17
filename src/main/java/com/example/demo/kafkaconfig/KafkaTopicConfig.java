package com.example.demo.kafkaconfig;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;

@Configuration
public class KafkaTopicConfig {

    @Bean
    public NewTopic andersenTopic(@Value("${kafka.topic}") String topic){
        return TopicBuilder.name(topic)
                .build();
    }
}
