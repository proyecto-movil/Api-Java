package com.example.ilenguageapi.service;

import com.example.ilenguageapi.domain.model.Subscription;
import com.example.ilenguageapi.domain.service.SubscriptionService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

public class SubscriptionServiceImpl  implements SubscriptionService {




    @Override
    public Page<Subscription> getAllSubscriptions(Pageable pageable) {
        return null;
    }

    @Override
    public Subscription createSubscription(Subscription subscription) {
        return null;
    }

    @Override
    public Subscription updateSubscription(int subscriptionId, Subscription subscriptions) {
        return null;
    }

    @Override
    public ResponseEntity<?> deleteSubscription(int subscriptionId) {
        return null;
    }

    @Override
    public Subscription getBySubscriptionId(int subscriptionId) {
        return null;
    }

    @Override
    public Subscription getByName(String name) {
        return null;
    }

    @Override
    public Subscription getByDuration(int monthDuration) {
        return null;
    }
}
