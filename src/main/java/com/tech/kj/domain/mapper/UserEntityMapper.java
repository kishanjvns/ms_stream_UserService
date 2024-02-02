package com.tech.kj.domain.mapper;

import com.tech.kj.domain.Contacts;
import com.tech.kj.domain.Emails;
import com.tech.kj.domain.Users;
import com.tech.kj.web.dto.RegisterUserDto;
import com.tech.kj.web.dto.RegisterUserDtoResponse;
import com.tech.kj.web.dto.UserDto;
import org.springframework.stereotype.Component;

import java.util.Set;

@Component
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
    public RegisterUserDtoResponse entityToDto(Users user){
        Contacts contact = user.getContacts().stream().filter(e->e.isPrimary()).findFirst().get();
        Emails email = user.getEmails().stream().filter(e-> e.isPrimary()).findFirst().get();
        return RegisterUserDtoResponse.builder()
                .contact(contact!=null?contact.getContact():"")
                .isPrimaryContact(contact!=null?contact.isPrimary():false)
                .email(email!=null?email.getEmail():"")
                .isPrimaryEmail(email!=null?email.isPrimary():false)
                .userName(user.getUserName())
                .firstName(user.getFirstName())
                .roles(user.getRoles())
                .lastName(user.getLastName())
                .id(String.valueOf(user.getId()))
                .build();
    }
}
