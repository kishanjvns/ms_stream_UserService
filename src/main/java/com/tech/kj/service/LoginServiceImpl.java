package com.tech.kj.service;

import com.tech.kj.JwtTokenProvider;
import com.tech.kj.web.dto.LoginDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class LoginServiceImpl implements  LoginService{
    private final AuthenticationManager authenticationManager;
    private final JwtTokenProvider jwtTokenUtil;
    @Override
    public String login(LoginDto loginDto) {
        final Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginDto.getUserName(),
                        loginDto.getPassword()
                )
        );
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String authorities = authentication.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.joining(","));
        String userName =authentication.getName();
        log.info("userName:{} and authorities: {} used to generate the token",userName,authorities);
        final String token = jwtTokenUtil.generateToken(authorities,userName);
        log.info("returning jwt token {} for login request of {}",token,loginDto);
        return token;
    }
}
