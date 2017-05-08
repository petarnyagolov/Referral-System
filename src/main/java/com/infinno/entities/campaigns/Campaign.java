package com.infinno.entities.campaigns;

import com.infinno.entities.events.UserEvent;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;
import java.util.HashSet;
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

    private String name;

    private Date startDate;

    private Date endDate;


    private String description;

    private String eventType;




    @ManyToMany
    @JoinTable(name = "user_event_campaign",
            joinColumns = @JoinColumn(name = "campaign_id"), inverseJoinColumns = @JoinColumn(name = "event_id"))
    private Set<UserEvent> userEvents;

    public Campaign() {
        this.userEvents=new HashSet<>();

    }

    public String getEventType() {
        return eventType;
    }

    public void setEventType(String eventType) {
        this.eventType = eventType;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public Set<UserEvent> getUserEvents() {
        return userEvents;
    }

    public void setUserEvents(Set<UserEvent> userEvents) {
        this.userEvents = userEvents;
    }

    public Set<UserEvent> getUserEvent() {
        return userEvents;
    }

    public void setUserEvent(Set<UserEvent> userEvent) {
        this.userEvents = userEvent;
    }

    public void addEvent(UserEvent userEvent){
        this.userEvents.add(userEvent);
    }
}
