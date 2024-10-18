package com.example.mysterybox.service;

import com.example.mysterybox.model.TreasureType;
import com.example.mysterybox.repository.TreasureTypeRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TreasureService {

    private final TreasureTypeRepository treasureTypeRepository;

    public TreasureService(TreasureTypeRepository treasureTypeRepository) {
        this.treasureTypeRepository = treasureTypeRepository;
    }

    public List<TreasureType> getAllTreasures() {

        return treasureTypeRepository.findAll();  // Return all treasures
    }
    public List<TreasureType> getAllTreasureTypes() {
        return treasureTypeRepository.findAll();
    }

}
