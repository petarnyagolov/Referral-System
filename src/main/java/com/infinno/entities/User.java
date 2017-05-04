package com.infinno.entities;

import com.infinno.entities.events.UserEvent;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "users")
public class User implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Size(min = 3,message = "Username is too short.")
    private String username;

    @Size(min = 4,message = "Password is too short")
    private String password;

    @ManyToOne
    @JoinColumn(name = "event_id")
    private UserEvent event;


    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "referralIdentifier_id")
    private ReferralIdentifier referralIdentifier;



    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "users_roles",
    joinColumns = @JoinColumn(name ="user_id"),inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<Role> authorities;


    public User() {
        this.authorities=new HashSet<>();
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Override
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public UserEvent getEvent() {
        return event;
    }

    public void setEvent(UserEvent event) {
        this.event = event;
    }

    public ReferralIdentifier getReferralIdentifier() {
        return referralIdentifier;
    }

    public void setReferralIdentifier(ReferralIdentifier referralIdentifier) {
        this.referralIdentifier = referralIdentifier;
    }

    @Override
    public Set<Role> getAuthorities() {
        return authorities;
    }

    public void setAuthorities(Set<Role> authorities) {
        this.authorities = authorities;
    }
    public void addDefaultRole(Role role){


        authorities.add(role);
    }
}
