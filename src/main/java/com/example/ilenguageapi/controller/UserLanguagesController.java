package com.example.ilenguageapi.controller;

import com.example.ilenguageapi.domain.model.LanguageOfInterest;
import com.example.ilenguageapi.domain.model.User;
import com.example.ilenguageapi.domain.service.LanguageOfInterestService;
import com.example.ilenguageapi.domain.service.UserService;
import com.example.ilenguageapi.resource.LanguageOfInterestResource;
import com.example.ilenguageapi.resource.SaveLanguageOfInterestResource;
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
import org.w3c.dom.stylesheets.LinkStyle;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api")
public class UserLanguagesController {
    @Autowired
    private UserService userService;
    @Autowired
    private LanguageOfInterestService languageOfInterestService;
    @Autowired
    private ModelMapper modelMapper;

    @Operation(summary = "assing language to user", description = "assign language to user by langueageId and userId", tags = {"languageUsers"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "assigned language", content = @Content(mediaType = "application/json")),
            @ApiResponse(responseCode = "404", description = "language not found or User not found")
    })
    @PostMapping("/users/{userId}/languages/{languageId}")
    public UserResource assignLanguageUser(
            @PathVariable Long userId,
            @PathVariable Long languageId) {
        return convertToResourceUser(userService.assignLanguageById(userId, languageId));
    }

    @Operation(summary = "unassing language to user", description = "Unassign language to user by langueageId and userId", tags = {"languageUsers"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "unassigned language", content = @Content(mediaType = "application/json")),
            @ApiResponse(responseCode = "404", description = "language not found or User not found")
    })
    @DeleteMapping("/users/{userId}/languages/{languageId}")
    public UserResource unassignLanguageUser(
            @PathVariable Long userId,
            @PathVariable Long languageId) {
        return convertToResourceUser(userService.unassignLanguageById(userId, languageId));
    }

    @Operation(summary = "GetAllLenguageByUserId", description = "Get all lenaguages of interest by userId and pageable", tags = {"languageUsers"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "All languages resturned", content = @Content(mediaType = "application/json")),
            @ApiResponse(responseCode = "404", description = "User not found")
    })
    @GetMapping("/users/{userId}/languages")
    public Page<LanguageOfInterestResource> getAllLanguageByUserId(
            @PathVariable Long userId,
            Pageable pageable) {
        Page<LanguageOfInterest> languagePage = languageOfInterestService.getAllLanguageByUserId(userId, pageable);
        List<LanguageOfInterestResource> resources = languagePage.getContent().stream()
                .map(this::convertToResourceLanguage).collect(Collectors.toList());
        return new PageImpl<>(resources, pageable, resources.size());
    }


    private User convertToEntityUser(SaveUserResource resource) {
        return modelMapper.map(resource, User.class);
    }

    private UserResource convertToResourceUser(User entity) {
        return modelMapper.map(entity, UserResource.class);
    }

    private LanguageOfInterest convertToEntityLanguage(SaveLanguageOfInterestResource resource) {
        return modelMapper.map(resource, LanguageOfInterest.class);
    }

    private LanguageOfInterestResource convertToResourceLanguage(LanguageOfInterest entity) {
        return modelMapper.map(entity, LanguageOfInterestResource.class);
    }
}
