package com.example.ilenguageapi.service;

import com.example.ilenguageapi.domain.model.Schedule;
import com.example.ilenguageapi.domain.repository.ScheduleRepository;
import com.example.ilenguageapi.domain.service.ScheduleService;
import com.example.ilenguageapi.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;


@Service
public class ScheduleServicelmpl  implements ScheduleService {

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
    public Schedule updateSchedule(int scheduleId, Schedule schedule) {
        return null;
    }

    @Override
    public ResponseEntity<?> deleteSchedule(int scheduleId) {
        return null;
    }

    @Override
    public Schedule getByName(String nameCourse) {
        return scheduleRepository.findByNameCourse(nameCourse)
                .orElseThrow(()-> new ResourceNotFoundException("Schedule", "NameCourse", nameCourse));
    }

    @Override
    public Schedule getByDuration(int hoursDuration) {
        return scheduleRepository.findByHoursDuration(hoursDuration)
                .orElseThrow(()-> new ResourceNotFoundException("Schedule", "HoursDuration", hoursDuration));
    }


}
