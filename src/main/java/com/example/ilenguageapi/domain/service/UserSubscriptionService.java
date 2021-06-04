package com.example.ilenguageapi.domain.service;

import com.example.ilenguageapi.domain.model.UserSubscription;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface UserSubscriptionService {
    Page <UserSubscription> getAllUserSubscriptions(Pageable pageable);
    Page<UserSubscription> getByUSerId(Pageable pageable, int userId);
    UserSubscription assignUserSubscription(int userId, int subscriptionId);
    UserSubscription unassingUserSubscription(int userId);

}
