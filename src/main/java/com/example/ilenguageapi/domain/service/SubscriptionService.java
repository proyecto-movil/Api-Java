package com.example.ilenguageapi.domain.service;

import com.example.ilenguageapi.domain.model.Subscription;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

public interface SubscriptionService {
    Page<Subscription> getAllSubscriptions(Pageable pageable);
    Subscription createSubscription(Subscription subscription);
    Subscription updateSubscription(int subscriptionId, Subscription subscriptions);
    ResponseEntity<?> deleteSubscription(int subscriptionId);
    Subscription getBySubscriptionId(int subscriptionId);
    Subscription getByName(String name);
    Subscription getByDuration(int monthDuration);
    Subscription getByPrice(int price);
}
