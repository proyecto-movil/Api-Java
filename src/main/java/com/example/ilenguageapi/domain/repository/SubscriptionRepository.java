package com.example.ilenguageapi.domain.repository;


import com.example.ilenguageapi.domain.model.Subscription;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SubscriptionRepository extends JpaRepository<Subscription, Integer> {
    public Optional<Subscription> findByName(String name);
    public Optional<Subscription> findByPrice(int price);
    public Optional<Subscription>findByMonthDuration(int monthDuration);

}
