package com.example.mysterybox.controller;

import com.example.mysterybox.model.User;
import com.example.mysterybox.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/v1/auth")
public class AuthController {

    @Autowired
    private AuthService authService;

    // POST /api/v1/auth/register
    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@RequestBody Map<String, String> request) {
        String username = request.get("username");
        String email = request.get("email");
        String password = request.get("password");
        String referralCode = request.get("referralCode");  // The user’s own referral code (optional)
        String referrerCode = request.get("referrerCode");  // Referrer's code (optional)

        try {
            User newUser = authService.registerUser(username, email, password, referralCode, referrerCode);
            Map<String, Object> response = Map.of(
                    "success", true,
                    "data", Map.of(
                            "userId", newUser.getId(),
                            "username", newUser.getUsername(),
                            "email", newUser.getEmail(),
                            "referralCode", newUser.getReferralCode(),
                            "credits", newUser.getCredits(),
                            "token", "some-jwt-token"  // This is a placeholder for a real JWT
                    )
            );
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(Map.of("success", false, "message", e.getMessage()));
        }
    }

    // POST /api/v1/auth/login
    @PostMapping("/login")
    public ResponseEntity<?> loginUser(@RequestBody Map<String, String> request) {
        String email = request.get("email");
        String password = request.get("password");

        try {
            User user = authService.login(email, password);
            Map<String, Object> response = Map.of(
                    "success", true,
                    "data", Map.of(
                            "userId", user.getId(),
                            "username", user.getUsername(),
                            "email", user.getEmail(),
                            "referralCode", user.getReferralCode(),
                            "credits", user.getCredits(),
                            "token", "some-jwt-token"  // You should generate a real JWT token here
                    )
            );
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            return ResponseEntity.status(401).body(Map.of("success", false, "message", e.getMessage()));
        }
    }
}
