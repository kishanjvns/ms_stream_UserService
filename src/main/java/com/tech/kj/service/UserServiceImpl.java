package com.tech.kj.service;

import com.tech.kj.domain.ContactEntity;
import com.tech.kj.domain.EmailEntity;
import com.tech.kj.domain.UserEntity;
import com.tech.kj.domain.mapper.UserEntityMapper;
import com.tech.kj.domain.types.UserStatus;
import com.tech.kj.repository.UserRepository;
import com.tech.kj.web.dto.UserDto;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
@Slf4j
public class UserServiceImpl implements UserService{
    private UserRepository userRepository;
    public UserServiceImpl(UserRepository userRepository){
        this.userRepository = userRepository;
    }

    @Override
    @Transactional()
    public Long save(UserDto userDto){
        log.info("service call to persist user has invoked");
        UserEntity userEntity = UserEntityMapper.mapToEntity(userDto);
        UserEntity savedEntity = userRepository.save(userEntity);
        return  savedEntity.getId();
    }


}
