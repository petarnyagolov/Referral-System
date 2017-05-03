package com.infinno.entities.events;

import com.infinno.entities.User;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@DiscriminatorValue("new")
public class NewUserEvent extends UserEvent {

    @OneToOne
    @JoinColumn(name = "new_user_id")
    private User newUsers;

    public User getNewUsers() {
        return newUsers;
    }

    public void setNewUsers(User newUsers) {
        this.newUsers = newUsers;
    }
}
