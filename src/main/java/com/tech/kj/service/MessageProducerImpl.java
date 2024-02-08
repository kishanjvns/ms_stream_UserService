package com.tech.kj.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
@Slf4j
public class MessageProducerImpl implements MessageProducer{
    private final KafkaTemplate kafkaTemplate;
    @Override
    public void publish(String topicName, String message) {
        log.info("producing message:{}  to topic {}",message,topicName);
        kafkaTemplate.send(topicName,message);
    }
}
