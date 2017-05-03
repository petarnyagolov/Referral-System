package com.infinno.models.dto;

public abstract class EventDto {

    private String referralUserHash;

    private String description;

    public String getReferralUserHash() {
        return referralUserHash;
    }

    public void setReferralUserHash(String referralUserHash) {
        this.referralUserHash = referralUserHash;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
