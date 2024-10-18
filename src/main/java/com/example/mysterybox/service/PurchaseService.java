package com.example.mysterybox.service;

import com.example.mysterybox.model.MysteryPurchase;
import com.example.mysterybox.model.TreasureType;
import com.example.mysterybox.model.User;
import com.example.mysterybox.repository.MysteryPurchaseRepository;
import com.example.mysterybox.repository.TreasureTypeRepository;
import com.example.mysterybox.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PurchaseService {

    private final MysteryPurchaseRepository mysteryPurchaseRepository;

    private final TreasureTypeRepository treasureTypeRepository;

    private final UserRepository userRepository;

    public PurchaseService(MysteryPurchaseRepository mysteryPurchaseRepository, TreasureTypeRepository treasureTypeRepository, UserRepository userRepository) {
        this.mysteryPurchaseRepository = mysteryPurchaseRepository;
        this.treasureTypeRepository = treasureTypeRepository;
        this.userRepository = userRepository;
    }

    public MysteryPurchase purchaseTreasure(String username, int quantity) throws Exception {
        Optional<User> userOptional = userRepository.findByUsername(username);
        if (userOptional.isEmpty()) {
            throw new Exception("User not found");
        }

        User user = userOptional.get();
        int price = 100 * quantity;  // Assume each treasure costs 100 credits

        // Check if user has enough credits
        if (user.getCredits() < price) {
            throw new Exception("Insufficient credits");
        }

        // Find a random available treasure
        Optional<TreasureType> treasureTypeOptional = treasureTypeRepository.findAll()
                .stream()
                .filter(t -> t.getRemainingQuantity() > 0)
                .findAny();

        if (treasureTypeOptional.isEmpty()) {
            throw new Exception("No treasures available");
        }

        TreasureType treasureType = treasureTypeOptional.get();
        treasureType.setRemainingQuantity(treasureType.getRemainingQuantity() - quantity);
        treasureTypeRepository.save(treasureType);

        // Log the purchase
        MysteryPurchase purchase = new MysteryPurchase();
        purchase.setUser(user);
        purchase.setTreasureType(treasureType);
        purchase.setPrice(price);
        mysteryPurchaseRepository.save(purchase);

        // Update user credits
        user.setCredits(user.getCredits() - price);
        userRepository.save(user);

        return purchase;
    }
}
