package com.example.mysterybox.repository;

import com.example.mysterybox.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByReferralCode(String referralCode);
    Optional<User> findByEmail(String email);
    Optional<User> findByUsername(String username);  // Added for checking username uniqueness
    Optional<User> findByCredits(double credits);


}
