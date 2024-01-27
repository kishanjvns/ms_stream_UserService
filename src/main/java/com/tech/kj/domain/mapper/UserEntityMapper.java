package com.tech.kj.domain.mapper;

import com.tech.kj.domain.Contacts;
import com.tech.kj.domain.Emails;
import com.tech.kj.domain.Users;
import com.tech.kj.web.dto.RegisterUserDto;
import com.tech.kj.web.dto.UserDto;

import java.util.Set;

public class UserEntityMapper {

    public static Users mapToEntity(RegisterUserDto request) {
        Contacts contactEntity = Contacts.builder()
                .isVerified(true)
                .isPrimary(request.getIsPrimaryContact())
                .contact(request.getContact())
                .build();
        Emails emailEntity = Emails.builder()
                .isVerified(true)
                .isPrimary(request.getIsPrimaryEmail())
                .email(request.getEmail())
                .build();
        var user = Users.builder()
                .firstName(request.getFirstName())
                .lastName(request.getLastName())
                .userName(request.getUserName())
                .contacts(Set.of(contactEntity))
                .emails(Set.of(emailEntity))
                .password(request.getPassword())
                .roles(request.getRoles())
                .build();
        contactEntity.setUser(user);
        emailEntity.setUser(user);
        return user;
    }
}
