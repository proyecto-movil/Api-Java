package com.example.ilenguageapi.domain.repository;

import com.example.ilenguageapi.domain.model.User;
import com.example.ilenguageapi.domain.model.UserSchedule;
import com.example.ilenguageapi.domain.model.UserSubscription;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;


import java.util.List;
import java.util.Optional;

public interface UserScheduleRepository extends JpaRepository<UserSchedule, Integer> {


    @Query( "SELECT us FROM UserSchedule us WHERE us.user.id = ?1")
    public List<UserSchedule>listByScheduleId(Long scheduleId);

    @Query("SELECT us FROM UserSchedule us WHERE us.user.id=?1 and us.schedule.id = ?2")
    public Optional<UserSchedule>findByUserIdAndScheduleId(Long userId, Long scheduleId);

    @Query(value = "select * from user_schedules us where us.user_id = ?1 ", nativeQuery = true)
    public Optional<UserSchedule>findLastUserScheduleByUserId(Long userId);

    @Query(value = "select * from user_schedule us where us.user_id = ?1", nativeQuery = true)
    public Page<UserSchedule> listByUserId(Long userId, Pageable pageable);

}
