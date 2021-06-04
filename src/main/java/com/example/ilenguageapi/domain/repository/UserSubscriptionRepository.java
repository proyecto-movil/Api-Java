package com.example.ilenguageapi.domain.repository;

import com.example.ilenguageapi.domain.model.UserSubscription;
import com.stripe.model.PaymentIntent;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

public interface UserSubscriptionRepository extends JpaRepository<UserSubscription, Integer> {
    //@Query(value = "SELECT us FROM UserSubscription us WHERE us.userId IN :userId")
    //public List<UserSubscription>listByUserId(@Param("userId") Collection<Integer> userId);

    @Query( "SELECT us FROM UserSubscription us WHERE us.user.id = ?1")
    public List<UserSubscription>listBySubscriptionId(int subscriptionId);

    @Query("SELECT us FROM UserSubscription us WHERE us.user.id=?1 and us.subscription.id = ?2")
    public Optional<UserSubscription>findByUserIdAndSubscriptionId(int userId, int subscriptionId);

    @Query(value = "select * from user_subscriptions us where us.user_id = ?1 order by us.final_date desc limit 1", nativeQuery = true)
    public Optional<UserSubscription>findLastUSerSubscriptionByUserId(int userId);

    @Modifying
    @Query(value= "update user_subscriptions us set us.final_date = ?1 where us.user_subscription_id = ?2", nativeQuery = true)
    public void unassingUserSubscription(LocalDateTime currentDate, int userSubscriptionId);
    
    @Query(value = "select * from user_subscriptions us where us.user_id = ?1", nativeQuery = true)
    public Page<UserSubscription> listByUserId(int userId, Pageable pageable);
    
}
