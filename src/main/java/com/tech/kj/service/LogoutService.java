package com.tech.kj.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.tech.kj.web.dto.LoginDto;

public interface LogoutService {
    public void logout(String token) throws JsonProcessingException;
}
