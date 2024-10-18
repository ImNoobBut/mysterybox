package com.example.mysterybox.controller;

import com.example.mysterybox.model.MysteryPurchase;
import com.example.mysterybox.model.TreasureType;
import com.example.mysterybox.service.PurchaseService;
import com.example.mysterybox.service.TreasureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/market")
public class PurchaseController {

    private final PurchaseService purchaseService;
    private final TreasureService treasureTypeService;
    private final SimpMessagingTemplate messagingTemplate;

    @Autowired
    public PurchaseController(PurchaseService purchaseService, TreasureService treasureTypeService, SimpMessagingTemplate messagingTemplate) {
        this.purchaseService = purchaseService;
        this.treasureTypeService = treasureTypeService;
        this.messagingTemplate = messagingTemplate;
    }

    // POST /api/v1/market/purchase
    @PostMapping("/purchase")
    public ResponseEntity<?> purchaseTreasure(@RequestBody Map<String, Integer> request) {
        int quantity = request.get("quantity");  // Extract purchase quantity from request

        // Retrieve the username (or email) from the current authenticated user
        // In production, you would get this from the SecurityContext
        // String currentUser = SecurityContextHolder.getContext().getAuthentication().getName();
        String currentUser = "admin";  // Placeholder username for now

        try {
            MysteryPurchase purchase = purchaseService.purchaseTreasure(currentUser, quantity);

            // Broadcast inventory update to all connected clients via WebSocket
            broadcastInventoryUpdate();

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

    // Helper method to broadcast the updated inventory via WebSocket
    private void broadcastInventoryUpdate() {
        List<TreasureType> treasures = treasureTypeService.getAllTreasureTypes();
        messagingTemplate.convertAndSend("/topic/inventory", treasures);
    }

    // GET /api/v1/market/mystery-boxes
    @GetMapping("/mystery-boxes")
    public ResponseEntity<?> getInventory() {
        List<TreasureType> treasures = treasureTypeService.getAllTreasureTypes();
        Map<String, Object> response = Map.of(
                "success", true,
                "data", Map.of(
                        "treasures", treasures
                )
        );
        return ResponseEntity.ok(response);
    }
}
