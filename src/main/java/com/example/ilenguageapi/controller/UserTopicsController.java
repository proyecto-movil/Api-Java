package com.example.ilenguageapi.controller;

import com.example.ilenguageapi.domain.model.TopicOfInterest;
import com.example.ilenguageapi.domain.model.User;
import com.example.ilenguageapi.domain.service.TopicOfInterestService;
import com.example.ilenguageapi.domain.service.UserService;
import com.example.ilenguageapi.resource.SaveTopicOfInterestResource;
import com.example.ilenguageapi.resource.SaveUserResource;
import com.example.ilenguageapi.resource.TopicOfInterestResource;
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
public class UserTopicsController {
    @Autowired
    private UserService userService;
    @Autowired
    private TopicOfInterestService topicOfInterestService;

    @Autowired
    private ModelMapper modelMapper;

    @Operation(summary = "assign topic to user", description = "Assign topic to user by topicId and userId", tags = {"topicUsers"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Assigned topic", content = @Content(mediaType = "application/json")),
            @ApiResponse(responseCode = "404", description = "Topic not found or User not found")
    })
    @PostMapping("/users/{userId}/topics/{topicId}")
    public UserResource assignTopicUser(
            @PathVariable Long userId,
            @PathVariable Long topicId) {
        return convertToResourceUser(userService.assignTopicById(userId, topicId));
    }

    @Operation(summary = "unassign topic to user", description = "Unassign topic to user by topicId and userId", tags = {"topicUsers"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "unassigned topic", content = @Content(mediaType = "application/json")),
            @ApiResponse(responseCode = "404", description = "Topic not found or User not found")
    })
    @DeleteMapping("/users/{userId}/topics/{topicId}")
    public UserResource unassignTopicUser(
            @PathVariable Long userId,
            @PathVariable Long topicId) {
        return convertToResourceUser(userService.unassignTopicById(userId, topicId));
    }

    @Operation(summary = "GetAllTopicsByUserId", description = "Get All topics by userId and pageable", tags = {"topicUsers"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "ALl topics returned", content = @Content(mediaType = "application/json")),
            @ApiResponse(responseCode = "404", description = "User not found")
    })
    @GetMapping("/users/{userId}/topics")
    public Page<TopicOfInterestResource> getAllTopicByUserId(
            @PathVariable Long userId,
            Pageable pageable) {

        Page<TopicOfInterest> topicPage = topicOfInterestService.getAllTopicsByUserId(userId, pageable);
        List<TopicOfInterestResource> resources = topicPage.getContent().stream()
                .map(this::convertToResourceTopic).collect(Collectors.toList());
        return new PageImpl<>(resources, pageable, resources.size());
    }

    private User convertToEntityUser(SaveUserResource resource) {
        return modelMapper.map(resource, User.class);
    }

    private UserResource convertToResourceUser(User entity) {
        return modelMapper.map(entity, UserResource.class);
    }

    private TopicOfInterest convertToEntityTopic(SaveTopicOfInterestResource resource) {
        return modelMapper.map(resource, TopicOfInterest.class);
    }

    private TopicOfInterestResource convertToResourceTopic(TopicOfInterest entity) {
        return modelMapper.map(entity, TopicOfInterestResource.class);
    }
}
