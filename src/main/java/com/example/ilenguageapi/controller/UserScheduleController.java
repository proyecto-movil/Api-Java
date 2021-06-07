package com.example.ilenguageapi.controller;

import com.example.ilenguageapi.domain.model.User;
import com.example.ilenguageapi.domain.model.UserSchedule;
import com.example.ilenguageapi.domain.service.UserScheduleService;
import com.example.ilenguageapi.resource.SaveUserScheduleResource;
import com.example.ilenguageapi.resource.UserScheduleResource;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api")
public class UserScheduleController {
    @Autowired
    private UserScheduleService userScheduleService;
    @Autowired
    private ModelMapper modelMapper;

    private UserSchedule convertToEntity(SaveUserScheduleResource resource){
        return modelMapper.map(resource, UserSchedule.class);
    }
    private UserScheduleResource convertToResource(UserSchedule entity) {
        return modelMapper.map(entity,UserScheduleResource.class);
    }

    @Operation(summary="Assing user to schedule", description="Assing user to  schedule", tags = {"user schedules"})
    @PostMapping("/users/{userId}/schedules/{scheduleId}")
    public UserSchedule assignUserSchedule(@RequestParam(name="userId") Long userId, @RequestParam(name="scheduleId") Long scheduleId){
        return userScheduleService.assignUserSchedule(userId,scheduleId);
    }



}
