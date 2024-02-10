package com.tech.kj.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
@RequiredArgsConstructor
@Slf4j
public class LogoutServiceImpl implements LogoutService {
    private final MessageProducer messageProducer;
    @Value("${kafka.topicName.gateway}")
    private String topicGateway;
    public void logout(String token) throws JsonProcessingException {
        log.info("logout service started");
        ObjectMapper mapper= new ObjectMapper();
        String jsonMessage=mapper.writeValueAsString(createLogoutActionMap(token));
        messageProducer.publish(topicGateway,jsonMessage);
    }
    public Map<String,String> createLogoutActionMap(String token){
        Map<String,String> map =new HashMap<>();
        map.put("action","markExpireJWT");
        map.put("jwtToken",token);
        return map;
    }
}
