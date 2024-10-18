package com.example.mysterybox.service;

import com.example.mysterybox.model.User;
import com.example.mysterybox.repository.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


    public User getUserById(Long userId) {
        return userRepository.findById(userId).orElseThrow(() -> new RuntimeException("User not found"));
    }

    public boolean validateReferralCode(String referralCode) {
        return userRepository.findByReferralCode(referralCode).isPresent();
    }

    public User getUserByCredit(Long userId) {
        return userRepository.findByCredits(userId).orElseThrow(() -> new RuntimeException("User not found"));
    }
    public User getUserByEmail(String email) {
        return userRepository.findByEmail(email).orElseThrow(() -> new RuntimeException("User not found"));
    }
}
