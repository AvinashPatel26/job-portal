package com.jobportal.repository;

import com.jobportal.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    // Used during login (AuthService)
    Optional<User> findByEmail(String email);

    // Useful for validations (register)
    boolean existsByEmail(String email);
    long countByRole(String role);

    List<User> findByRole(String role);

}
