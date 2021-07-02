package com.example.ilenguageapi.controller;

import com.example.ilenguageapi.domain.model.TopicOfInterest;
import com.example.ilenguageapi.domain.service.TopicOfInterestService;
import com.example.ilenguageapi.resource.SaveTopicOfInterestResource;
import com.example.ilenguageapi.resource.SaveUserResource;
import com.example.ilenguageapi.resource.TopicOfInterestResource;
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
public class TopicOfInterestController {

    @Autowired
    private TopicOfInterestService topicService;
    @Autowired
    private ModelMapper modelMapper;

    private TopicOfInterest convertToEntity(SaveTopicOfInterestResource resource) {
        return modelMapper.map(resource, TopicOfInterest.class);
    }

    private TopicOfInterestResource convertToRecource(TopicOfInterest entity) {
        return modelMapper.map(entity, TopicOfInterestResource.class);
    }

    @Operation(summary = "Add topic", description = "Create new topic", tags = {"topics"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Topic created", content = @Content(mediaType = "application/json"))
    })
    @PostMapping("/topic")
    public TopicOfInterestResource createTopic(@Valid @RequestBody SaveTopicOfInterestResource resource) {
        TopicOfInterest topic = convertToEntity(resource);
        return convertToRecource(topicService.createTopic(topic));
    }

    @Operation(summary = "Get all topics", description = "Get all topics by pages", tags = {"topics"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "All topic returned", content = @Content(mediaType = "application/json")),
            @ApiResponse(responseCode = "404", description = "Topics not found")

    })
    @GetMapping("/topic")
    public Page<TopicOfInterestResource> getAllTopics(Pageable pageable) {
        Page<TopicOfInterest> topicPage = topicService.getAllTopics(pageable);
        List<TopicOfInterestResource> resources = topicPage.getContent()
                .stream()
                .map(this::convertToRecource)
                .collect(Collectors.toList());
        return new PageImpl<>(resources, pageable, resources.size());
    }
    @Operation(summary = "Delete topic", description = "Deleted topic by topicId", tags = {"topics"})
    @ApiResponses(value = {
           @ApiResponse(responseCode = "200", description = "Topic deleted", content = @Content(mediaType ="application/json"))
    })
    @DeleteMapping("/topic/{topicId}")
    public ResponseEntity<?> deleteTopic(@PathVariable Long topicId){
        return topicService.deleteTopic(topicId);
    }
}
