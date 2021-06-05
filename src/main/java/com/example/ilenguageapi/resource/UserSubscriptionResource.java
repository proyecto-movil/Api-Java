package com.example.ilenguageapi.resource;

import com.example.ilenguageapi.domain.model.Subscription;
import com.example.ilenguageapi.domain.model.User;

import java.time.LocalDateTime;

public class UserSubscriptionResource {
    private int userSubscriptionId;

    private LocalDateTime initialDate;
    private LocalDateTime finalDate;
    private User user;
    private Subscription subscription;

    public int getUserSubscriptionId() {
        return userSubscriptionId;
    }

    public void setUserSubscriptionId(int userSubscriptionId) {
        this.userSubscriptionId = userSubscriptionId;
    }

    public LocalDateTime getInitialDate() {
        return initialDate;
    }

    public void setInitialDate(LocalDateTime initialDate) {
        this.initialDate = initialDate;
    }

    public LocalDateTime getFinalDate() {
        return finalDate;
    }

    public void setFinalDate(LocalDateTime finalDate) {
        this.finalDate = finalDate;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Subscription getSubscription() {
        return subscription;
    }

    public void setSubscription(Subscription subscription) {
        this.subscription = subscription;
    }
}
