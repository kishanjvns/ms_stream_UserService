package com.tech.kj.domain.mapper;

import com.tech.kj.domain.ContactEntity;
import com.tech.kj.domain.EmailEntity;
import com.tech.kj.domain.UserEntity;
import com.tech.kj.domain.types.UserStatus;
import com.tech.kj.web.dto.UserDto;

import java.util.Set;

public class UserEntityMapper {

    public static UserEntity mapToEntity(UserDto userDto) {
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
