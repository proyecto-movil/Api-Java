package com.example.ilenguageapi.controller;

import com.example.ilenguageapi.domain.model.LanguageOfInterest;
import com.example.ilenguageapi.domain.model.TopicOfInterest;
import com.example.ilenguageapi.domain.service.LanguageOfInterestService;
import com.example.ilenguageapi.resource.LanguageOfInterestResource;
import com.example.ilenguageapi.resource.SaveLanguageOfInterestResource;
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
public class LanguageOfInterestController {
    @Autowired
    private LanguageOfInterestService languageOfInterestService;
    @Autowired
    private ModelMapper modelMapper;

    private LanguageOfInterest convertToEntity(SaveLanguageOfInterestResource resource) {
        return modelMapper.map(resource, LanguageOfInterest.class);
    }

    private LanguageOfInterestResource convertToResource(LanguageOfInterest entity) {
        return modelMapper.map(entity, LanguageOfInterestResource.class);
    }

    @Operation(summary = "Add language", description = "Create new language", tags = {"languages"})
    @PostMapping("/languages")
    public LanguageOfInterestResource createLanguage(@Valid @RequestBody SaveLanguageOfInterestResource resource){
        LanguageOfInterest language = convertToEntity(resource);
        return convertToResource(languageOfInterestService.createLanguage(language));
    }

    @Operation(summary = "Get all language", description = "Get all language by pages", tags = {"languages"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "All language returned", content = @Content(mediaType = "application/json")),
            @ApiResponse(responseCode = "404", description = "Languages not found")
    })
    @GetMapping("/languages")
    public Page<LanguageOfInterestResource> getAllLanguages(Pageable pageable) {
        Page<LanguageOfInterest> languagePage = languageOfInterestService.getAllTopics(pageable);
        List<LanguageOfInterestResource> resources = languagePage.getContent()
                .stream()
                .map(this::convertToResource)
                .collect(Collectors.toList());
        return new PageImpl<>(resources, pageable, resources.size());
    }

    @Operation(summary = "Delete language", description = "Deleted language by languageId", tags = {"languages"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Language deleted", content = @Content(mediaType ="application/json"))
    })
    @DeleteMapping("/language/{languageId}")
    public ResponseEntity<?> deleteLanguage(@PathVariable Long languageId) {
        return languageOfInterestService.deleteLanguage(languageId);
    }
}