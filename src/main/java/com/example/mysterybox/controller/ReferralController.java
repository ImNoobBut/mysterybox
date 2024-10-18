package com.example.mysterybox.controller;

import com.example.mysterybox.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/v1/referral")
public class ReferralController {

    @Autowired
    private UserService userService;

    // GET /api/v1/referral/validate
    @GetMapping("/validate")
    public ResponseEntity<?> validateReferralCode(@RequestParam String referralCode) {
        boolean isValid = userService.validateReferralCode(referralCode);
        return ResponseEntity.ok(Map.of("valid", isValid));
    }
}
