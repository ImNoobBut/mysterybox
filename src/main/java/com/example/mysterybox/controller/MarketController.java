package com.example.mysterybox.controller;

import com.example.mysterybox.model.TreasureType;
import com.example.mysterybox.service.TreasureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/market")
public class MarketController {

    @Autowired
    private TreasureService treasureService;

    // GET /api/v1/market/inventory
    @GetMapping("/inventory")
    public ResponseEntity<?> getInventory() {
        List<TreasureType> treasures = treasureService.getAllTreasures();

        Map<String, Object> response = Map.of(
                "success", true,
                "data", Map.of("treasures", treasures)
        );
        return ResponseEntity.ok(response);
    }
}
