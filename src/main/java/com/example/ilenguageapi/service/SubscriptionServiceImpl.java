package com.example.ilenguageapi.service;

import com.example.ilenguageapi.domain.model.Subscription;
import com.example.ilenguageapi.domain.repository.SubscriptionRepository;
import com.example.ilenguageapi.domain.service.SubscriptionService;
import com.example.ilenguageapi.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class SubscriptionServiceImpl  implements SubscriptionService {

    @Autowired
    private SubscriptionRepository subscriptionRepository;


    @Override
    public Page<Subscription> getAllSubscriptions(Pageable pageable) {
        return subscriptionRepository.findAll(pageable);
    }

    @Override
    public Subscription createSubscription(Subscription subscription) {
        return subscriptionRepository.save(subscription);
    }

    @Override
    public Subscription updateSubscription(int subscriptionId, Subscription subscriptions) {
        //? would this work ok?
        Subscription subscription = subscriptionRepository.findById(subscriptionId)
                .orElseThrow(()->new ResourceNotFoundException("Subscription", "Id", subscriptionId));
        subscription.setPrice(subscriptions.getPrice());
        subscription.setName(subscriptions.getName());
        subscription.setMonthDuration(subscriptions.getMonthDuration());
        return subscriptionRepository.save(subscription);
    }

    @Override
    public ResponseEntity<?> deleteSubscription(int subscriptionId) {
        Subscription subscription = subscriptionRepository.findById(subscriptionId)
                .orElseThrow(()-> new ResourceNotFoundException("Subscription", "Id", subscriptionId));
        subscriptionRepository.delete(subscription);
        return ResponseEntity.ok().build();
    }

    @Override
    public Subscription getBySubscriptionId(int subscriptionId) {
        return subscriptionRepository.findById(subscriptionId)
                .orElseThrow(()->new ResourceNotFoundException("Subscription", "Id", subscriptionId));
    }

    @Override
    public Subscription getByName(String name) {
        return subscriptionRepository.findByName(name)
                .orElseThrow(()-> new ResourceNotFoundException("Subscription", "Name", name));
    }

    @Override
    public Subscription getByDuration(int monthDuration) {
        return subscriptionRepository.findByMonthDuration(monthDuration)
                .orElseThrow(()-> new ResourceNotFoundException("Subscription", "MonthDuration", monthDuration));
    }

    @Override
    public Subscription getByPrice(int price) {
        return subscriptionRepository.findByPrice(price)
                .orElseThrow(()-> new ResourceNotFoundException("Subscription", "Price", price));
    }
}
