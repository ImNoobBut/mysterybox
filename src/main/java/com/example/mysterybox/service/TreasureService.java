package com.example.mysterybox.service;

import com.example.mysterybox.model.TreasureType;
import com.example.mysterybox.repository.TreasureTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TreasureService {

    @Autowired
    private TreasureTypeRepository treasureTypeRepository;

    public List<TreasureType> getAllTreasures() {
        return treasureTypeRepository.findAll();  // Return all treasures
    }
}
