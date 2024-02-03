package com.tech.kj.repository;

import com.tech.kj.domain.Contacts;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface ContactRepository extends JpaRepository<Contacts,String> {
    @Query("select c from Contacts c where c.contact=:contact")
    public Optional<Contacts> findByContactNumber(@Param("contact")String contact);
}
