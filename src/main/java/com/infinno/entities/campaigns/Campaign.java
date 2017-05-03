package com.infinno.entities.campaigns;

import com.infinno.entities.events.UserEvent;

import javax.persistence.*;
import java.util.Date;
import java.util.Set;

@Entity
@Table(name = "campaigns")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(discriminatorType = DiscriminatorType.STRING)
@DiscriminatorValue("campaign")
public  abstract class Campaign {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String description;

    private Date startDate;

    private Date endDate;

    private String eventType;

    @ManyToMany
    @JoinTable(name = "user_event_campaign",
            joinColumns = @JoinColumn(name ="campaign_id"),inverseJoinColumns = @JoinColumn(name = "event_id"))
    private Set<UserEvent> userEvent;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
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

    public String getEventType() {
        return eventType;
    }

    public void setEventType(String eventType) {
        this.eventType = eventType;
    }

    public Set<UserEvent> getUserEvent() {
        return userEvent;
    }

    public void setUserEvent(Set<UserEvent> userEvent) {
        this.userEvent = userEvent;
    }
}
