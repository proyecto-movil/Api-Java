package com.example.ilenguageapi.controller;

import com.example.ilenguageapi.domain.model.Badget;
import com.example.ilenguageapi.domain.service.BadgetService;
import com.example.ilenguageapi.resource.BadgetResource;
import com.example.ilenguageapi.resource.SaveBadgetResource;
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
public class BadgetController {
    @Autowired
    private BadgetService badgetService;
    @Autowired
    private ModelMapper modelMapper;

    private Badget convertToEntity(SaveBadgetResource resource) {
        return modelMapper.map(resource, Badget.class);
    }

    private BadgetResource convertToResource(Badget entity) {
        return modelMapper.map(entity, BadgetResource.class);
    }

    @Operation(summary = "Add Badget", description = "Create new badget", tags = {"badgets"})
    @PostMapping("/badgets")
    public BadgetResource createBadget(@Valid @RequestBody SaveBadgetResource resource) {
        Badget badget = convertToEntity(resource);
        return convertToResource(badgetService.createBadget(badget));
    }

    @Operation(summary = "Get all badgets", description = "Get all badgest by pages", tags = {"badgets"})
    @ApiResponses( value = {
           @ApiResponse(responseCode = "200", description = "All badgets returned", content = @Content(mediaType = "application/json")),
           @ApiResponse(responseCode = "404", description = "Badgets not found")
    })
    @GetMapping("/badgets")
    public Page<BadgetResource> getAllBadgets(Pageable pageable) {
        Page<Badget> badgetPage = badgetService.getAllBadgets(pageable);
        List<BadgetResource> resources = badgetPage.getContent()
                .stream()
                .map(this::convertToResource)
                .collect(Collectors.toList());
        return new PageImpl<>(resources, pageable, resources.size());
    }
    @Operation(summary = "Delete badget", description = "Deletecd badget by badgetId", tags = {"badgets"})
    @ApiResponses(value = {
           @ApiResponse(responseCode = "200", description = "Badget deleted", content = @Content(mediaType = "application/json"))
    })
    @DeleteMapping("/badgets/{badgetId}")
    public ResponseEntity<?> deleteBadget(@PathVariable Long badgetId){
        return badgetService.deleteBadget(badgetId);
    }
}
