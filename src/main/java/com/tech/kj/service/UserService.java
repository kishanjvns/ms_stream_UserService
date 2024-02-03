package com.tech.kj.service;

import com.tech.kj.web.dto.RegisterUserDto;
import com.tech.kj.web.dto.RegisterUserDtoResponse;
import com.tech.kj.web.dto.UserDto;

public interface UserService {
    String save(RegisterUserDto userDto);
    RegisterUserDtoResponse findUserByUserName(String userName);
}
