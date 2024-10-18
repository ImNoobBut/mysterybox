package com.example.mysterybox.model;

import lombok.Data;
import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Data
@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true, length = 50)
    private String username;

    @Column(nullable = false, unique = true, length = 100)
    private String email;

    @Column(name = "password_hash", nullable = false, length = 255)
    private String passwordHash;

    @Column(name = "referral_code", unique = true, length = 10)
    private String referralCode;

    @ManyToOne
    @JoinColumn(name = "referrer_id", referencedColumnName = "id")
    private User referrer;  // A user can have another user as a referrer

    @Column(precision = 10)
    private double credits = 1000;

    @Column(name = "member_level", nullable = false)
    private int memberLevel = 0;

    @Column(name = "created_at", updatable = false)
    private LocalDateTime createdAt = LocalDateTime.now();

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<MysteryPurchase> mysteryPurchases;  // User can have many purchases

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<CreditTransaction> creditTransactions;  // Track credit changes
}
