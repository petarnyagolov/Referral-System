package com.infinno.entities.campaigns;

import javax.persistence.*;

@Entity
@DiscriminatorValue("discountpercent")
public  abstract class DiscountPercentTopupCampaign  extends Campaign{


}
