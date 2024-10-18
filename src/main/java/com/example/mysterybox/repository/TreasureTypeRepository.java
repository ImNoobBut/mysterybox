package com.example.mysterybox.repository;

import com.example.mysterybox.model.TreasureType;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TreasureTypeRepository extends JpaRepository<TreasureType, Long> {
    Optional<TreasureType> findByName(String name);
    Optional<TreasureType> findByDescription(String description);
    Optional<TreasureType> findByImageUrl(String imageUrl);
}
