package com.tech.kj.service;

import com.tech.kj.domain.ContactEntity;
import com.tech.kj.domain.EmailEntity;
import com.tech.kj.domain.UserEntity;
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
        UserEntity userEntity = mapToEntity(userDto);
        UserEntity savedEntity = userRepository.save(userEntity);
        return  savedEntity.getId();
    }

    private UserEntity mapToEntity(UserDto userDto) {
        return  UserEntity.builder()
                .firstName(userDto.getFirstName())
                .lastName(userDto.getLastName())
                .userName(userDto.getUserName())
                .password(userDto.getPassword())
                .userStatus(UserStatus.INIT)
                .isDeleted(false)
                .emails(Set.of(EmailEntity.builder()
                                .email(userDto.getEmail())
                                .isPrimary(userDto.isPrimaryEmail())
                        //TODO take isVerified from request, in future
                                .isVerified(true)
                        .build()))
                .contacts(Set.of(ContactEntity.builder()
                                .contact(userDto.getContact())
                                .isPrimary(userDto.isPrimaryContact())
                                .isVerified(true)
                        .build()))
                .build();
    }
}
