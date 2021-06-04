package com.example.ilenguageapi.controller;


import com.example.ilenguageapi.domain.model.Schedule;
import com.example.ilenguageapi.domain.service.ScheduleService;
import com.example.ilenguageapi.resource.SaveScheduleResource;
import com.example.ilenguageapi.resource.ScheduleResource;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
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
    @Operation(summary="Get all", description="Get all Schedule", tags = {"schedules"} )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description="All schedule returned", content = @Content(mediaType = "application/json"))
    })
    @GetMapping("/schedules")
    public Page<ScheduleResource> getAllSchedule(Pageable pageable){
        List<ScheduleResource> schedule = scheduleService.getAllSchedule(pageable)
                .getContent().stream().map(this::convertToResource)
                .collect(Collectors.toList());
        return new PageImpl<>(schedule, pageable,  schedule.size());
    }
    @Operation(summary="Save Schedule", description="Save schedule", tags = {"schedules"} )
    @PostMapping("/schedules")
    public ScheduleResource createSchedule(@Valid @RequestBody SaveScheduleResource resource){
        return convertToResource(scheduleService.createSchedule(convertToEntity(resource)));
    }
    @Operation(summary="Update schedule", description="Update schedule", tags = {"schedules"} )
    @PutMapping ("schedules/{id}")
    public ScheduleResource updateSchedule(@PathVariable(name="id") int scheduleId, @Valid @RequestBody SaveScheduleResource resource){
        return convertToResource(scheduleService.updateSchedule(scheduleId,convertToEntity(resource)));
    }
    @Operation(summary="Delete schedule", description="Delete schedule", tags = {"schedules"} )
    @DeleteMapping("schedules/{id}")
    public ResponseEntity<?> deleteSchedule(@PathVariable(name="id") int scheduleId){
        return scheduleService.deleteSchedule(scheduleId);
    }
    @Operation(summary="Get by id", description="Get the schedules giving an id", tags = {"schedules"} )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description="All schedule returned", content = @Content(mediaType = "application/json")),
            @ApiResponse(responseCode = "404", description="Schedule Not Found", content = @Content(mediaType = "application/json"))

    })
    @GetMapping("/schedules/{id}")
    public ScheduleResource getScheduleById(@PathVariable(name="id") int scheduleId){
        return convertToResource(scheduleService.getByScheduleId(scheduleId));
    }




    @Operation(summary="Get by Day", description="Get the schedule giving a day", tags = {"schedules"} )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description="All schedule returned", content = @Content(mediaType = "application/json")),
            @ApiResponse(responseCode = "404", description="Schedule Not Found", content = @Content(mediaType = "application/json"))

    })
    @GetMapping("/schedules/day/{day}")
    public ScheduleResource getScheduleByDay(@PathVariable(name="day") String scheduleDay){
        return convertToResource(scheduleService.getByDay(scheduleDay));
    }





}
