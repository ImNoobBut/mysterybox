package com.example.mysterybox.service;

import com.example.mysterybox.model.TreasureType;
import com.example.mysterybox.repository.TreasureTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TreasureTypeService {

    @Autowired
    private TreasureTypeRepository treasureTypeRepository;

    public List<TreasureType> getAllTreasureTypes() {
        return treasureTypeRepository.findAll();
    }
}
