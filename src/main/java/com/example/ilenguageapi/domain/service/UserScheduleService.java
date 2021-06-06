package com.example.ilenguageapi.domain.service;

import com.example.ilenguageapi.domain.model.UserSchedule;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface UserScheduleService {
    Page <UserSchedule> getAllUserSchedules(Pageable pageable);
    Page<UserSchedule> getByUSerId(Pageable pageable, Long userId);
    UserSchedule assignUserSchedule(Long userId, Long scheduleId);

}
