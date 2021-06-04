package com.example.ilenguageapi.service;

import com.example.ilenguageapi.domain.model.Schedule;
import com.example.ilenguageapi.domain.model.Subscription;
import com.example.ilenguageapi.domain.repository.ScheduleRepository;
import com.example.ilenguageapi.domain.service.ScheduleService;
import com.example.ilenguageapi.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;


@Service
public class ScheduleServiceImpl implements ScheduleService {

    @Autowired
    private ScheduleRepository scheduleRepository;


    @Override
    public Page<Schedule> getAllSchedule(Pageable pageable) {
        return scheduleRepository.findAll(pageable);
    }

    @Override
    public Schedule createSchedule(Schedule schedule) {
        return scheduleRepository.save(schedule);
    }

    @Override
    public Schedule updateSchedule(long scheduleId, Schedule schedules) {
        Schedule schedule= scheduleRepository.findById(scheduleId)
                .orElseThrow(()->new ResourceNotFoundException("Schedule", "Id", scheduleId));
        schedule.setDay(schedules.getDay());

        return scheduleRepository.save(schedule);


    }

    @Override
    public ResponseEntity<?> deleteSchedule(long scheduleId) {
        Schedule schedule = scheduleRepository.findById(scheduleId)
                .orElseThrow(()-> new ResourceNotFoundException("Schedule", "Id", scheduleId));
        scheduleRepository.delete(schedule);
        return ResponseEntity.ok().build();


    }

    @Override
    public Schedule getByScheduleId(long scheduleId) {
        return scheduleRepository.findById(scheduleId)
                .orElseThrow(()->new ResourceNotFoundException("Schedule", "Id", scheduleId));
    }

    @Override
    public Schedule getByDay(String day) {
        return scheduleRepository.findByDay(day)
                .orElseThrow(()-> new ResourceNotFoundException("Schedule", "Day", day));
    }






}
