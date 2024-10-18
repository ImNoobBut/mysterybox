package com.example.mysterybox.repository;

import com.example.mysterybox.model.MysteryPurchase;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MysteryPurchaseRepository extends JpaRepository<MysteryPurchase, Long> {
}
