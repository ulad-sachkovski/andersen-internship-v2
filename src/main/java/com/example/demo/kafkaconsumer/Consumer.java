package com.example.demo.kafkaconsumer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class Consumer {

    private static final Logger LOG = LoggerFactory.getLogger(KafkaListener.class);

    @KafkaListener(
            topics = "${kafka.topic}",
            groupId = "${kafka.groupid}"
    )
    void listenNewCity(String cityName){
        LOG.info("New city added: " + cityName);
    }

}
