package com.tech.kj.service;

import com.tech.kj.domain.Users;
import com.tech.kj.domain.mapper.UserEntityMapper;
import com.tech.kj.exception.ApplicationConstant;
import com.tech.kj.exception.RecordAlreadyExistException;
import com.tech.kj.repository.ContactRepository;
import com.tech.kj.repository.EmailRepository;
import com.tech.kj.repository.UserRepository;
import com.tech.kj.web.dto.RegisterUserDto;
import com.tech.kj.web.dto.RegisterUserDtoResponse;
import com.tech.kj.web.dto.UserDto;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Slf4j
@RequiredArgsConstructor
public class UserServiceImpl implements UserService{
    private final UserRepository userRepository;
    private final EmailRepository emailRepository;
    private final ContactRepository contactRepository;
    private final UserEntityMapper mapper;
    private final PasswordEncoder passwordEncoder;

    @Override
    @Transactional()
    public String save(RegisterUserDto request){
        log.info("service call to persist user has invoked");
        //validation check if username,email and contact number already present or not
        if(userRepository.loadByUserName(request.getUserName()).isPresent() || emailRepository.findByEmailAddress(request.getEmail()).isPresent() || contactRepository.findByContactNumber(request.getContact()).isPresent()){
            log.info("record already present");
            throw  new RecordAlreadyExistException(ApplicationConstant.ERR_ALREADY_EXIST,ApplicationConstant.ERR_ALREADY_EXIST_MSG);
        }
        request.setPassword(passwordEncoder.encode(request.getPassword()));
        Users userEntity = UserEntityMapper.mapToEntity(request);
        Users savedEntity = userRepository.save(userEntity);
        log.info("user saved into db with id: {}",savedEntity.getId());
        return  savedEntity.getId();
    }

    public RegisterUserDtoResponse findUserByUserName(String userName){
        Optional<Users> usersOptional= userRepository.loadByUserName(userName);
        if(usersOptional.isPresent()){
            return mapper.entityToDto(usersOptional.get());
        }
        throw new RuntimeException("404 user not found");
    }
}
