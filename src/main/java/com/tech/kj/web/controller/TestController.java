package com.tech.kj.web.controller;


import com.tech.kj.service.LoginService;
import com.tech.kj.service.UserService;
import com.tech.kj.web.dto.JwtAccessTokenResponse;
import com.tech.kj.web.dto.LoginDto;
import com.tech.kj.web.dto.RegisterUserDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@RestController
@RequestMapping("/users/api/v1/test")
@Slf4j
public class TestController {

    @GetMapping("/hi")
    @PreAuthorize("hasAuthority('ROLE_USER')")
    public String hello(){
        return "hello";
    }
}