package com.infinno.entities.campaigns;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("percent")
public class PercentTopupCampaign extends DiscountPercentTopupCampaign {
}
