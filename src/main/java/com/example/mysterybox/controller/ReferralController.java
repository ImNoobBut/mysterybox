package com.example.mysterybox.controller;

import com.example.mysterybox.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/api/v1/referral")
public class ReferralController {

    private final UserService userService;

    public ReferralController(UserService userService) {
        this.userService = userService;
    }

    // GET /api/v1/referral/validate
    @GetMapping("/validate")
    public ResponseEntity<?> validateReferralCode(@RequestParam String referralCode) {
        boolean isValid = userService.validateReferralCode(referralCode);
        return ResponseEntity.ok(Map.of("valid", isValid));
    }
}
