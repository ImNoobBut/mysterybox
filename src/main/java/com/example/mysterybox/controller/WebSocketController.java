package com.example.mysterybox.controller;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

@Controller
public class WebSocketController {

    // Simulate sending inventory updates in real-time
    @MessageMapping("/updateInventory")
    @SendTo("/topic/inventory")
    public String sendInventoryUpdate(String message) {
        // This would typically contain updated inventory data.
        return "Inventory Updated: " + message;
    }
}
