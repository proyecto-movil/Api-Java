package com.example.ilenguageapi.domain.model;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "user_subscriptions")
public class UserSubscription {
    @Id
    @GeneratedValue(strategy =GenerationType.IDENTITY)
    private int userSubscriptionId;

    private int subscriptionId;

    private int userId;

    private LocalDateTime initialDate;
    private LocalDateTime finalDate;

    @ManyToOne
    @JoinColumn(name = "subscriptionId", nullable = false, insertable = false, updatable = false)
    private Subscription subscription;

    @ManyToOne
    @JoinColumn(name ="userSubscriptionId", nullable = false , insertable = false, updatable = false)
    private User user;

    public UserSubscription(int userSubscriptionId, int subscriptionId, int userId, LocalDateTime initialDate, LocalDateTime finalDate, Subscription subscription, User user) {
        this.userSubscriptionId = userSubscriptionId;
        this.subscriptionId = subscriptionId;
        this.userId = userId;
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

    public int getSubscriptionId() {
        return subscriptionId;
    }

    public int getUserId() {
        return userId;
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

    public void setSubscriptionId(int subscriptionId) {
        this.subscriptionId = subscriptionId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
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
