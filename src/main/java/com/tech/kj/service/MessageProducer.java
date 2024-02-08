package com.tech.kj.service;

public interface MessageProducer {
    void publish(String topicName,String message);
}
