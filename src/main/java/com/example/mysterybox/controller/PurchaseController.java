package com.example.mysterybox.controller;

import com.example.mysterybox.model.MysteryPurchase;
import com.example.mysterybox.service.PurchaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/v1/market")
public class PurchaseController {

    @Autowired
    private PurchaseService purchaseService;

    // POST /api/v1/market/purchase
    @PostMapping("/purchase")
    public ResponseEntity<?> purchaseTreasure(@RequestBody Map<String, Integer> request) {
        int quantity = request.get("quantity");  // Extract purchase quantity from request

        // Retrieve the username (or email) from the current authenticated user
        String currentUser = SecurityContextHolder.getContext().getAuthentication().getName();

        try {
            MysteryPurchase purchase = purchaseService.purchaseTreasure(currentUser, quantity);
            Map<String, Object> response = Map.of(
                    "success", true,
                    "data", Map.of(
                            "purchaseId", purchase.getId(),
                            "treasureReceived", Map.of(
                                    "id", purchase.getTreasureType().getId(),
                                    "type", purchase.getTreasureType().getName(),
                                    "name", purchase.getTreasureType().getName()
                            ),
                            "remainingCredits", purchase.getUser().getCredits()
                    )
            );
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(Map.of("success", false, "message", e.getMessage()));
        }
    }
}
