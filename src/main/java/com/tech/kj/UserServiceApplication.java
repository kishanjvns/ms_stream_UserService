package com.tech.kj;

import com.tech.kj.domain.ContactEntity;
import com.tech.kj.domain.EmailEntity;
import com.tech.kj.domain.UserEntity;
import com.tech.kj.repository.UserRepository;
import com.tech.kj.dao.read.UserRepositoryRead;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Optional;
import java.util.Set;

@SpringBootApplication
public class UserServiceApplication implements CommandLineRunner {

	@Autowired
	private UserRepository userRepository;
	@Autowired
	private UserRepositoryRead userReadRepository;
	public static void main(String[] args) {
		SpringApplication.run(UserServiceApplication.class, args);

	}


	@Override
	public void run(String... args) throws Exception {
		UserEntity user = UserEntity.builder().firstName("Kishan")
				.lastName("Jaiswal")
				.emails(Set.of(EmailEntity.builder()
						.email("abc@gmail.com")
						.isPrimary(true)
						.isVerified(true)
						.build()))
				.isDeleted(false)
				.contacts(Set.of(ContactEntity.builder()
						.contact("123456789")
						.isVerified(true)
						.build()))
				.build();
		UserEntity savedUser = userRepository.save(user);
		System.out.println("Saved User"+savedUser.getId());
		Optional<UserEntity> fetchedUser = userReadRepository.findById(savedUser.getId());
		System.out.println("fetched user"+ fetchedUser.isPresent());

	}
}
