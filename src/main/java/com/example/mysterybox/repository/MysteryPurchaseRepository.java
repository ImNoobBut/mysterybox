package com.example.mysterybox.repository;

import com.example.mysterybox.model.MysteryPurchase;
import com.example.mysterybox.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MysteryPurchaseRepository extends JpaRepository<MysteryPurchase, Long> {

}
