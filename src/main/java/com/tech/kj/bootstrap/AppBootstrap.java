package com.tech.kj.bootstrap;

import com.tech.kj.domain.ContactEntity;
import com.tech.kj.domain.EmailEntity;
import com.tech.kj.domain.UserEntity;
import com.tech.kj.domain.types.UserStatus;
import com.tech.kj.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Set;

@Component
@Slf4j
public class AppBootstrap implements CommandLineRunner {
    private UserRepository userRepository;
    public AppBootstrap(UserRepository userRepository){
        this.userRepository = userRepository;
    }
    @Override
    public void run(String... args) throws Exception {
        log.info("bootstrap started");
        seedUserEntity();
    }
    public void seedUserEntity(){
        for(int i=0; i < 5; i++){
           UserEntity userEntity = createUserEntityInstance(i);
           UserEntity savedEntity = userRepository.save(userEntity);
           log.info("user saved with id: {}",savedEntity.getId());
        }
    }
    public UserEntity createUserEntityInstance(int recordCount){
        return  UserEntity.builder()
                .firstName("Kishan")
                .lastName("jaiswal")
                .userName("abc"+recordCount)
                .password("password")
                .userStatus(UserStatus.INIT)
                .isDeleted(false)
                .emails(Set.of(EmailEntity.builder()
                        .email(String.format("abc%d@gmail.com",recordCount))
                        .isPrimary(true)
                        //TODO take isVerified from request, in future
                        .isVerified(true)
                        .build()))
                .contacts(Set.of(ContactEntity.builder()
                        .contact("12345678"+recordCount)
                        .isPrimary(true)
                        .isVerified(true)
                        .build()))
                .build();
    }
}
