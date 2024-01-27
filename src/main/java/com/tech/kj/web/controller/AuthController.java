package com.tech.kj.web.controller;


import com.tech.kj.service.LoginService;
import com.tech.kj.service.UserService;
import com.tech.kj.web.dto.JwtAccessTokenResponse;
import com.tech.kj.web.dto.LoginDto;
import com.tech.kj.web.dto.RegisterUserDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@RestController
@RequestMapping("/users/api/v1/auth")
@Slf4j
public class AuthController {
    private final UserService userService;
    private final LoginService loginService;

    public AuthController(UserService userService,LoginService loginService) {
        this.userService = userService;
        this.loginService = loginService;
    }
    @PostMapping("/login")
    public ResponseEntity<JwtAccessTokenResponse> login(@RequestBody LoginDto loginDto) {
        log.info("API /login invoked with {}", loginDto);
        String token = loginService.login(loginDto);
        return ResponseEntity.ok(JwtAccessTokenResponse.builder().access_token(token).build());
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