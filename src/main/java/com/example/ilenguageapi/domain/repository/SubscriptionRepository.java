package com.example.ilenguageapi.domain.repository;


import com.example.ilenguageapi.domain.model.Subscription;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SubscriptionRepository extends JpaRepository<Subscription, Integer> {
    @Query("SELECT s FROM Subscription s WHERE s.name = ?1")
    public Optional<Subscription> findByName(String name);
    public Optional<Subscription> findByPrice(int price);
    public Optional<Subscription>findByMonthDuration(int monthDuration);

}
