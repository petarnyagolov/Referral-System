package com.infinno.models.bindingModels;

import com.infinno.annotations.PresentOrFuture;
import com.infinno.entities.events.UserEvent;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.*;
import java.util.Date;
import java.util.Set;


public class AddCampaignBindingModel {

    @NotNull
    @Size(min = 3, max = 10, message = "Invalid name")
    private String name;

    @NotNull
    @Size(min = 5, max = 300, message = "Invalid description")
    private String description;

    @NotNull
    @Size(max = 50, message = "Invalid side effects")
    private String sideEffects;

    @NotNull
    @Pattern(regexp = "^.*[Cc]orp.*$", message = "Invalid creator")
    private String referral;

    private Boolean isNeedAnotherCampaign;

    @NotNull(message = "Type cannot be null")
    private String type;


    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @PresentOrFuture(message = "The release date should be in a future moment")
    private Date startDate;


    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @PresentOrFuture(message = "The release date should be in a future moment")
    private Date endDate;

    @NotNull
    private Set<UserEvent> userEvents;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getSideEffects() {
        return sideEffects;
    }

    public void setSideEffects(String sideEffects) {
        this.sideEffects = sideEffects;
    }

    public String getReferral() {
        return referral;
    }

    public void setReferral(String referral) {
        this.referral = referral;
    }

    public Boolean getNeedAnotherCampaign() {
        return isNeedAnotherCampaign;
    }

    public void setNeedAnotherCampaign(Boolean needAnotherCampaign) {
        isNeedAnotherCampaign = needAnotherCampaign;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
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

    public Set<UserEvent> getUserEvents() {
        return userEvents;
    }

    public void setUserEvents(Set<UserEvent> userEvents) {
        this.userEvents = userEvents;
    }
}
