package com.example.mysterybox.model;

import lombok.Data;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "mystery_purchases")
public class MysteryPurchase {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne
    @JoinColumn(name = "treasure_type_id", nullable = false)
    private TreasureType treasureType;

    @Column(nullable = false, precision = 10)
    private double price;

    @Column(name = "purchased_at", updatable = false)
    private LocalDateTime purchasedAt = LocalDateTime.now();
}
