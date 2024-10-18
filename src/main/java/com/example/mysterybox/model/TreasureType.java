package com.example.mysterybox.model;

import lombok.Data;

import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Data
@Entity
@Table(name = "treasure_types")
public class TreasureType {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 100)
    private String name;

    @Column(columnDefinition = "TEXT")
    private String description;

    @Column(name = "initial_quantity", nullable = false)
    private int initialQuantity = 100;

    @Column(name = "remaining_quantity", nullable = false)
    private int remainingQuantity = 100;

    @Column(name = "image_url", length = 255)
    private String imageUrl;

    @Column(name = "created_at", updatable = false)
    private LocalDateTime createdAt = LocalDateTime.now();

    @OneToMany(mappedBy = "treasureType", cascade = CascadeType.ALL)
    private List<MysteryPurchase> mysteryPurchases;  // List of purchases for this treasure type
}
