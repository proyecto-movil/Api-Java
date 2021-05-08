package com.example.ilenguageapi.controller;


import com.example.ilenguageapi.domain.model.Schedule;
import com.example.ilenguageapi.domain.service.ScheduleService;
import com.example.ilenguageapi.resource.SaveScheduleResource;
import com.example.ilenguageapi.resource.ScheduleResource;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api")
public class ScheduleController {
    @Autowired
    private ScheduleService scheduleService;
    @Autowired
    private ModelMapper mapper;

    private Schedule convertToEntity(SaveScheduleResource resource){
        return mapper.map(resource, Schedule.class);
    }
    private ScheduleResource convertToResource(Schedule entity){
        return mapper.map(entity, ScheduleResource.class);
    }

    @GetMapping("/schedule")
    public Page<ScheduleResource> getAllSubscriptions(Pageable pageable){
        List<ScheduleResource> schedule = scheduleService.getAllSchedule(pageable)
                .getContent().stream().map(this::convertToResource)
                .collect(Collectors.toList());
        return new PageImpl<>(schedule, pageable,  schedule.size());
    }

    @PostMapping("/schedule")
    public ScheduleResource createSchedule(@Valid @RequestBody SaveScheduleResource resource){
        return convertToResource(scheduleService.createSchedule(convertToEntity(resource)));
    }

    @PutMapping ("schedule/{id}")
    public ScheduleResource updateSchedule(@PathVariable(name="id") int scheduleId, @Valid @RequestBody SaveScheduleResource resource){
        return convertToResource(scheduleService.updateSchedule(scheduleId,convertToEntity(resource)));
    }

    @DeleteMapping("schedule/{id}")
    public ResponseEntity<?> deleteSchedule(@PathVariable(name="id") int scheduleId){
        return scheduleService.deleteSchedule(scheduleId);
    }

    @GetMapping("/schedule/{nameCourse}")
    public ScheduleResource getScheduleByName(@PathVariable(name="nameCourse") String scheduleName){
        return convertToResource(scheduleService.getByName(scheduleName));
    }


    @GetMapping("/schedule/{hoursDuration}")
    public ScheduleResource getScheduleByHoursDuration(@PathVariable(name="hoursDuration") int scheduleHoursDuration){
    return convertToResource(scheduleService.getByDuration(scheduleHoursDuration));
    }


}
