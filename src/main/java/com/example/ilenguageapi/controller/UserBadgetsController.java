package com.example.ilenguageapi.controller;

import com.example.ilenguageapi.domain.model.Badget;
import com.example.ilenguageapi.domain.model.User;
import com.example.ilenguageapi.domain.service.BadgetService;
import com.example.ilenguageapi.domain.service.UserService;
import com.example.ilenguageapi.resource.BadgetResource;
import com.example.ilenguageapi.resource.SaveBadgetResource;
import com.example.ilenguageapi.resource.SaveUserResource;
import com.example.ilenguageapi.resource.UserResource;
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

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api")
public class UserBadgetsController {
    @Autowired
    private UserService userService;
    @Autowired
    private BadgetService badgetService;
    @Autowired
    private ModelMapper modelMapper;

    private User convertToEntityUser(SaveUserResource resource) {
        return modelMapper.map(resource, User.class);
    }
    private UserResource convertToResourceUser(User entity) {
        return modelMapper.map(entity, UserResource.class);
    }
    private Badget converToEntityBadget(SaveBadgetResource resource){
       return modelMapper.map(resource,Badget.class);
    }
    private BadgetResource convertToResourceBadget(Badget entity){
       return modelMapper.map(entity, BadgetResource.class);
    }
    @Operation(summary = "assign badget to user", description = "assign badget to user by ids", tags = {"userBadgets"})
    @ApiResponses(value = {
           @ApiResponse(responseCode = "200", description = "assigned badget", content = @Content(mediaType = "application/json")),
           @ApiResponse(responseCode = "404", description = "badget not found or user not found")
    })
    @PostMapping("/users/{userId}/badgets/{badgetId}")
    public UserResource assignUserBadgets(
           @PathVariable Long userId,
           @PathVariable Long badgetId){
       return convertToResourceUser(userService.assignBadgetById(userId,badgetId));
    }
    @Operation(summary = "unassign badget to user", description = "unassign badget to user by ids", tags = {"userBadgets"})
    @ApiResponses(value = {
           @ApiResponse(responseCode = "200", description = "unassigned badget", content = @Content(mediaType = "application/json")),
           @ApiResponse(responseCode = "404", description = "badget not found or user not found")
    })
    @DeleteMapping("/users/{userId}/badgets/{badgetId}")
    public UserResource unassignUserBadgets(
           @PathVariable Long userId,
           @PathVariable Long badgetId){
       return convertToResourceUser(userService.unassignBadgetById(userId,badgetId));
    }

    @Operation(summary = "GetAllBadgetsByUserId", description = "Get all badgets by userId and pageable", tags = {"userBadgets"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "All Badgets returned", content = @Content(mediaType = "application/json")),
            @ApiResponse(responseCode = "404", description = "User not found")
    })
    @GetMapping("/users/{userId}/badgets")
    public Page<BadgetResource> getAllBadgetsByUserId(
            @PathVariable Long userId,
            Pageable pageable){
        Page<Badget> badgetsPage = badgetService.getAllBadgetsByUserId(userId,pageable);
        List<BadgetResource> resources = badgetsPage.getContent()
                .stream()
                .map(this::convertToResourceBadget)
                .collect(Collectors.toList());
        return new PageImpl<>(resources,pageable,resources.size());
    }
}
