package com.tech.kj.service;

import com.tech.kj.web.dto.RegisterUserDto;
import com.tech.kj.web.dto.UserDto;

public interface UserService {
    Long save(RegisterUserDto userDto);
}
