package com.example.ilenguageapi.domain.service;

import com.example.ilenguageapi.domain.model.Schedule;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

public interface ScheduleService {
    Page<Schedule> getAllSchedule(Pageable pageable);
    Schedule createSchedule(Schedule schedule);
    Schedule updateSchedule(Long scheduleId ,Schedule schedule);
    ResponseEntity<?> deleteSchedule(Long scheduleId);
    Schedule getByScheduleId(Long scheduleId);
    Schedule getByDay(String day);
}
