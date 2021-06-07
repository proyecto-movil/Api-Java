package com.example.ilenguageapi.domain.repository;


import com.example.ilenguageapi.domain.model.Subscription;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SubscriptionRepository extends JpaRepository<Subscription, Integer> {
    //!Query refers to the class (MODEL)
    @Query("SELECT s FROM Subscription s WHERE s.name = ?1")
    public Optional<Subscription> findByName(String name);

    //!Native query refers to the table atribute
    @Query(
            value = "SELECT * FROM subscriptions s WHERE s.price = ?1",
            nativeQuery = true
    )
    public Optional<Subscription> findByPrice(int price);

    @Query("SELECT s FROM Subscription s WHERE s.monthDuration = ?1")
    public Optional<Subscription>findByMonthDuration(int monthDuration);

}
