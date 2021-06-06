package com.example.ilenguageapi.domain.model;

import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "user_subscriptions")
public class UserSubscription {
    @Id
    @GeneratedValue(strategy =GenerationType.IDENTITY)
    private int userSubscriptionId;



    private LocalDateTime initialDate;
    private LocalDateTime finalDate;

    @JsonBackReference
    @ManyToOne
    @JoinColumn(name = "subscription_id", nullable = false)
    private Subscription subscription;

    @JsonBackReference
    @ManyToOne
    @JoinColumn(name ="user_id", nullable = false )
    private User user;

    public UserSubscription(int userSubscriptionId, LocalDateTime initialDate, LocalDateTime finalDate, Subscription subscription, User user) {
        this.userSubscriptionId = userSubscriptionId;
        this.initialDate = initialDate;
        this.finalDate = finalDate;
        this.subscription = subscription;
        this.user = user;
    }

    public UserSubscription() {
    }

    public int getUserSubscriptionId() {
        return userSubscriptionId;
    }



    public LocalDateTime getInitialDate() {
        return initialDate;
    }

    public LocalDateTime getFinalDate() {
        return finalDate;
    }

    public Subscription getSubscription() {
        return subscription;
    }

    public User getUser() {
        return user;
    }

    public void setUserSubscriptionId(int userSubscriptionId) {
        this.userSubscriptionId = userSubscriptionId;
    }

    public void setInitialDate(LocalDateTime initialDate) {
        this.initialDate = initialDate;
    }

    public void setFinalDate(LocalDateTime finalDate) {
        this.finalDate = finalDate;
    }

    public void setSubscription(Subscription subscription) {
        this.subscription = subscription;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
