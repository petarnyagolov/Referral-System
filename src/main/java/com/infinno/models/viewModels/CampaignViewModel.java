package com.infinno.models.viewModels;

import java.util.Date;

public class CampaignViewModel {

    private long id;

    private String campaignName;

    private String eventType;

    private String typeCampaign;

    private Date startDate;

    private Date endDate;

    private String description;




        public CampaignViewModel() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getCampaignName() {
        return campaignName;
    }

    public void setCampaignName(String campaignName) {
        this.campaignName = campaignName;
    }

    public String getEventType() {
        return eventType;
    }

    public void setEventType(String eventType) {
        this.eventType = eventType;
    }

    public String getTypeCampaign() {
        return typeCampaign;
    }

    public void setTypeCampaign(String typeCampaign) {
        this.typeCampaign = typeCampaign;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
