package com.example.mysterybox.service;

import com.example.mysterybox.model.User;
import com.example.mysterybox.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public User getUserById(Long userId) {
        return userRepository.findById(userId).orElseThrow(() -> new RuntimeException("User not found"));
    }

    public boolean validateReferralCode(String referralCode) {
        return userRepository.findByReferralCode(referralCode).isPresent();
    }
}
