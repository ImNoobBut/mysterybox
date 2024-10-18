package com.example.mysterybox.controller;

import com.example.mysterybox.model.User;
import com.example.mysterybox.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/v1/users")
public class UserController {

    @Autowired
    private UserService userService;

    // GET /api/v1/users/balance
    @GetMapping("/balance")
    public ResponseEntity<?> getUserBalance() {
        try {
            User user = userService.getUserById(1L);  // Assume user ID is 1 for this example
            Map<String, Object> response = Map.of(
                    "success", true,
                    "data", Map.of(
                            "credits", user.getCredits(),
                            "totalPurchases", user.getMysteryPurchases() != null ? user.getMysteryPurchases().size() : 0  // Handle null safely
                    )
            );
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            return ResponseEntity.status(500).body(Map.of("success", false, "message", e.getMessage()));
        }
    }
}
