package com.example.mysterybox.service;

import com.example.mysterybox.model.User;
import com.example.mysterybox.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public User registerUser(String username, String email, String password, String referralCode, String referrerCode) throws Exception {
        if (userRepository.findByEmail(email).isPresent()) {
            throw new Exception("Email already in use");
        }

        if (referralCode != null && userRepository.findByReferralCode(referralCode).isPresent()) {
            throw new Exception("Referral code already in use");
        }

        User newUser = new User();
        newUser.setUsername(username);
        newUser.setEmail(email);
        newUser.setPasswordHash(passwordEncoder.encode(password));
        newUser.setReferralCode(referralCode != null ? referralCode : generateReferralCode());
        newUser.setCredits(1000.00);

        // Handle referrer logic (if any)
        if (referrerCode != null) {
            Optional<User> referrer = userRepository.findByReferralCode(referrerCode);
            referrer.ifPresent(newUser::setReferrer);
        }

        return userRepository.save(newUser);
    }

    public User login(String email, String password) throws Exception {
        Optional<User> userOptional = userRepository.findByEmail(email);
        if (userOptional.isPresent()) {
            User user = userOptional.get();
            if (passwordEncoder.matches(password, user.getPasswordHash())) {
                return user;  // Login successful
            } else {
                throw new Exception("Invalid password");
            }
        } else {
            throw new Exception("User not found");
        }
    }

    private String generateReferralCode() {
        return Long.toHexString(Double.doubleToLongBits(Math.random())).substring(0, 8);
    }
}
