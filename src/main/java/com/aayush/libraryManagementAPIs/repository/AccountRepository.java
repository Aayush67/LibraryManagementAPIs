package com.aayush.libraryManagementAPIs.repository;

import com.aayush.libraryManagementAPIs.model.entity.UserRegistration;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AccountRepository extends JpaRepository<UserRegistration, Long> {
    Optional<UserRegistration> findByEmail(String email);
}
