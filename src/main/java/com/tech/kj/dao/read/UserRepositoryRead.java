package com.tech.kj.dao.read;

import com.tech.kj.domain.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface UserRepositoryRead extends JpaRepository<UserEntity, Long> {
}
