package com.infinno.entities.campaigns;

import javax.persistence.*;
import java.math.BigDecimal;


@Entity
@DiscriminatorValue("topupcampaign")
public  class TopupCampaign extends Campaign {

    private BigDecimal sum;

    public BigDecimal getSum() {
        return sum;
    }

    public void setSum(BigDecimal sum) {
        this.sum = sum;
    }
}
