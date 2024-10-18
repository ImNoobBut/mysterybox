package com.example.mysterybox.repository;

import com.example.mysterybox.model.TreasureType;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TreasureTypeRepository extends JpaRepository<TreasureType, Long> {
}
