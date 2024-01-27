package com.tech.kj.service;

import com.tech.kj.domain.Users;
import com.tech.kj.domain.mapper.UserEntityMapper;
import com.tech.kj.repository.UserRepository;
import com.tech.kj.web.dto.RegisterUserDto;
import com.tech.kj.web.dto.UserDto;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class UserServiceImpl implements UserService{
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    public UserServiceImpl(UserRepository userRepository,PasswordEncoder passwordEncoder){
        this.userRepository = userRepository;
        this.passwordEncoder =passwordEncoder;
    }

    @Override
    @Transactional()
    public Long save(RegisterUserDto request){
        log.info("service call to persist user has invoked");
        request.setPassword(passwordEncoder.encode(request.getPassword()));
        Users userEntity = UserEntityMapper.mapToEntity(request);
        Users savedEntity = userRepository.save(userEntity);
        log.info("user saved into db with id: {}",savedEntity.getId());
        return  savedEntity.getId();
    }
}
