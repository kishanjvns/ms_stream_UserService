package com.tech.kj.repository;

import com.tech.kj.domain.Emails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface EmailRepository extends JpaRepository<Emails,String> {
    @Query("select e from Emails e where  e.email=:email")
    public Optional<Emails> findByEmailAddress(@Param("email") String email);
}
