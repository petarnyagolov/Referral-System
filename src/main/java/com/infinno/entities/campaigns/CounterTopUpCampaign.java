package com.infinno.entities.campaigns;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("counter")
public class CounterTopUpCampaign extends TopupCampaign {

    private long campaignCounter;

    public long getCampaignCounter() {
        return campaignCounter;
    }

    public void setCampaignCounter(long campaignCounter) {
        this.campaignCounter = campaignCounter;
    }
}
