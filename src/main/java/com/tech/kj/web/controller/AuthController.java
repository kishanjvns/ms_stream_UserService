package com.tech.kj.web.controller;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.tech.kj.service.LoginService;
import com.tech.kj.service.LogoutService;
import com.tech.kj.service.MessageProducer;
import com.tech.kj.service.UserService;
import com.tech.kj.web.dto.JwtAccessTokenResponse;
import com.tech.kj.web.dto.LoginDto;
import com.tech.kj.web.dto.RegisterUserDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/users/api/v1/auth")
@Slf4j
@RequiredArgsConstructor
public class AuthController {
    private final UserService userService;
    private final LoginService loginService;
    private final LogoutService logoutService;




    @PostMapping("/login")
    public ResponseEntity<JwtAccessTokenResponse> login(@RequestBody LoginDto loginDto) {
        log.info("API /login invoked with {}", loginDto);
        String token = loginService.login(loginDto);
        return ResponseEntity.ok(JwtAccessTokenResponse.builder().access_token(token).build());
    }

    @PostMapping("/logout")
    public ResponseEntity logout(@RequestHeader("Authorization") String authheader) throws JsonProcessingException {
        log.info("API /logout invoked with {}", authheader);
        logoutService.logout(authheader.substring(7));
        return ResponseEntity.ok().build();
    }
    @Transactional
    @PostMapping("/register")
    public ResponseEntity<JwtAccessTokenResponse> register(
            @RequestBody RegisterUserDto request) {
        log.info("API /register invoked with {}",request);
        userService.save(request);
        return ResponseEntity.created(URI.create("/api/v1/auth/login")).build();
    }

}