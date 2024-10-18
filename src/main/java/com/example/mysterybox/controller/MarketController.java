package com.example.mysterybox.controller;

import com.example.mysterybox.model.TreasureType;
import com.example.mysterybox.service.TreasureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/market")
public class MarketController {

    private final TreasureService treasureService;

    @Autowired
    public MarketController(TreasureService treasureService) {
        this.treasureService = treasureService;
    }

    // GET /api/v1/market/inventory
    @GetMapping("/inventory")
    public ResponseEntity<Map<String, Object>> getInventory() {
        try {
            List<TreasureType> treasures = treasureService.getAllTreasures();

            Map<String, Object> response = Map.of(
                    "success", true,
                    "data", Map.of("treasures", treasures)
            );

            return ResponseEntity.ok(response);
        } catch (Exception e) {
            // Log the exception (optional)
            // logger.error("Failed to retrieve inventory: ", e);
            return ResponseEntity.status(500).body(Map.of(
                    "success", false,
                    "message", "An error occurred while retrieving the inventory."
            ));
        }
    }
}
