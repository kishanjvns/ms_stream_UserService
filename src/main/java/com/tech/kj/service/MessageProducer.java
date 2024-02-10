package com.tech.kj.service;

import com.fasterxml.jackson.core.JsonProcessingException;

public interface MessageProducer {
    void publish(String topicName,String message);
}
