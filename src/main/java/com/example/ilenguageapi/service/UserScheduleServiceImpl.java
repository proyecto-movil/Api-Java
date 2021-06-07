package com.example.ilenguageapi.service;

import com.example.ilenguageapi.domain.model.*;
import com.example.ilenguageapi.domain.repository.ScheduleRepository;
import com.example.ilenguageapi.domain.repository.UserRepository;
import com.example.ilenguageapi.domain.repository.UserScheduleRepository;
import com.example.ilenguageapi.domain.service.UserScheduleService;
import com.example.ilenguageapi.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class UserScheduleServiceImpl implements UserScheduleService {

    @Autowired
    private UserScheduleRepository userScheduleRepository;
    @Autowired
    private ScheduleRepository scheduleRepository;
    @Autowired
    private UserRepository userRepository;

    @Override
    public Page<UserSchedule> getAllUserSchedules(Pageable pageable) {
        return userScheduleRepository.findAll(pageable);
    }

    @Override
    public Page<UserSchedule> getByUSerId(Pageable pageable, Long userId) {
        return (Page<UserSchedule>) userScheduleRepository.listByUserId(userId, pageable);
    }

    @Override
    public UserSchedule assignUserSchedule(Long userId, Long scheduleId) {
        Optional<UserSchedule> existingUserSchedule = userScheduleRepository.findLastUserScheduleByUserId(userId);
        if(existingUserSchedule.isPresent()){

                throw new ResourceNotFoundException("The user already Schedule");

        }
        User chosenUser = userRepository.findById((long) userId)
                .orElseThrow(()->new ResourceNotFoundException("User not found"));
        Schedule chosenSchedule = scheduleRepository.findById(scheduleId)
                .orElseThrow(()->new ResourceNotFoundException("Schedule not found"));;
        var userSchedule = new UserSchedule();

        userSchedule.setUser(chosenUser);
        userSchedule.setSchedule(chosenSchedule);
        return userScheduleRepository.save(userSchedule);
    }

}
