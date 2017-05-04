package com.infinno.entities.campaigns;

import javax.persistence.*;

@Entity
@DiscriminatorValue("discountpercent")
public  class DiscountPercentTopupCampaign  extends Campaign{


}
