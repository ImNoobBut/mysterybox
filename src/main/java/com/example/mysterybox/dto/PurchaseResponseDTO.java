package com.example.mysterybox.dto;

public class PurchaseResponseDTO {
    private Long purchaseId;
    private TreasureDTO treasureReceived;
    private int remainingCredits;

    // Constructor, getters, and setters

    public PurchaseResponseDTO(Long purchaseId, TreasureDTO treasureReceived, int remainingCredits) {
        this.purchaseId = purchaseId;
        this.treasureReceived = treasureReceived;
        this.remainingCredits = remainingCredits;
    }

    public Long getPurchaseId() {
        return purchaseId;
    }

    public void setPurchaseId(Long purchaseId) {
        this.purchaseId = purchaseId;
    }

    public TreasureDTO getTreasureReceived() {
        return treasureReceived;
    }

    public void setTreasureReceived(TreasureDTO treasureReceived) {
        this.treasureReceived = treasureReceived;
    }

    public int getRemainingCredits() {
        return remainingCredits;
    }

    public void setRemainingCredits(int remainingCredits) {
        this.remainingCredits = remainingCredits;
    }
}