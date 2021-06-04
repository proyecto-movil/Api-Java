package com.example.ilenguageapi.domain.repository;

import com.example.ilenguageapi.domain.model.UserSubscription;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

public interface UserSubscriptionRepository extends JpaRepository<UserSubscription, Integer> {
    @Query(value = "SELECT us FROM UserSubscription us WHERE us.userId IN :userId")
    public List<UserSubscription>listByUserId(@Param("userId") Collection<Integer> userId);

    @Query( "SELECT us FROM UserSubscription us WHERE us.subscriptionId = ?1")
    public List<UserSubscription>listBySubscriptionId(int subscriptionId);

    @Query("SELECT us FROM UserSubscription us WHERE us.userId=?1 and us.subscriptionId = ?2")
    public Optional<UserSubscription>findByUserIdAndSubscriptionId(int userId, int subscriptionId);

    /*@Query("SELECT us FROM UserSubscription us WHERE us.userId=?1 ORDER BY US.finalDate DESC")
    public Optional<UserSubscription>findLastUSerSubscriptionByUserId(int userId);*/
}
