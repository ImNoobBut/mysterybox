package com.example.mysterybox.repository;

import com.example.mysterybox.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByReferralCode(String referralCode);
    Optional<User> findByEmail(String email);
    Optional<User> findByUsername(String username);  // Added for checking username uniqueness
}
