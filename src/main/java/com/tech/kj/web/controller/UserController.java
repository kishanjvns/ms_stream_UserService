package com.tech.kj.web.controller;


import com.tech.kj.service.UserService;
import com.tech.kj.web.dto.RegisterUserDto;
import com.tech.kj.web.dto.RegisterUserDtoResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users/api/v1/user")
@Slf4j
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @GetMapping("/{userName}")
    @PreAuthorize("hasAuthority('ROLE_USER')")
    public ResponseEntity<RegisterUserDtoResponse> fetchUserByUserName(@PathVariable("userName") String userName){
        log.info("find user by username api invoked with param: {}",userName);
        RegisterUserDtoResponse responseDto= userService.findUserByUserName(userName);
        return ResponseEntity.ok(responseDto);
    }
}