package com.example.ilenguageapi.service;

import com.example.ilenguageapi.domain.model.Subscription;
import com.example.ilenguageapi.domain.model.UserSubscription;
import com.example.ilenguageapi.domain.repository.SubscriptionRepository;
import com.example.ilenguageapi.domain.repository.UserSubscriptionRepository;
import com.example.ilenguageapi.domain.service.UserSubscriptionService;
import com.example.ilenguageapi.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class UserSubscriptionServiceImpl implements UserSubscriptionService {

    @Autowired
    private UserSubscriptionRepository userSubscriptionRepository;
    @Autowired
    private SubscriptionRepository subscriptionRepository;

    @Override
    public Page<UserSubscription> getAllUserSubscriptions(Pageable pageable) {
        return userSubscriptionRepository.findAll(pageable);
    }

    @Override
    public UserSubscription createUserSubscription(UserSubscription userSubscription) {
        return null;
    }

    @Override
    public Page<UserSubscription> getByUSerId(Pageable pageable, int userId) {
        return null;
    }

    @Override
    public UserSubscription assignUserSubscription(int userId, int subscriptionId) {
        Optional<UserSubscription> existingUserSubscription = userSubscriptionRepository.findByUserIdAndSubscriptionId(userId, subscriptionId);
        if(existingUserSubscription.isPresent()){
            LocalDateTime foundDateTime = existingUserSubscription.get().getFinalDate();
            int compareValue = foundDateTime.compareTo(LocalDateTime.now());
            if(compareValue >0){
                throw new ResourceNotFoundException("The user already has an active subscription");
            }
        }

        Subscription chosenSubscription = subscriptionRepository.findById(subscriptionId)
                .orElseThrow(()-> new ResourceNotFoundException("There is not a subscription with the given Id"));
        var userSubscription = new UserSubscription();
        userSubscription.setInitialDate(LocalDateTime.now());
        userSubscription.setFinalDate(LocalDateTime.now().plusMonths(chosenSubscription.getMonthDuration()));
        userSubscription.setUserId(userId);
        userSubscription.setSubscriptionId(subscriptionId);
        return userSubscriptionRepository.save(userSubscription);
    }

    @Override
    public UserSubscription unassingUserSubscription(int userId) {
        return null;
    }
}
