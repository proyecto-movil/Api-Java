package com.example.ilenguageapi.controller;

import com.example.ilenguageapi.domain.model.Session;
import com.example.ilenguageapi.domain.service.SessionService;
import com.example.ilenguageapi.resource.SaveSessionResource;
import com.example.ilenguageapi.resource.SessionResource;
import io.cucumber.java.eo.Se;
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
public class UserSessionsController {
    @Autowired
    private ModelMapper mapper;

    @Autowired
    private SessionService sessionService;

    @Operation(summary = "Assign User to Session", description = "Establishes association between Session and User",
            tags = {"Sessions"}
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Session and user assigned", content = @Content(mediaType = "application/json")),
            @ApiResponse(responseCode = "404", description = "Session not found")
    })
    @PostMapping("/sessions/{sessionId}/users/{userId}")
    public SessionResource assignUserSession(@PathVariable Long sessionId, @PathVariable Long userId) {
        return convertToResource(sessionService.assignUserSession(userId, sessionId));
    }

    @Operation(summary = "Remove assignment between User and Session", description = "Ends association between Session and User",
            tags = {"Sessions"}
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Session and user unassigned", content = @Content(mediaType = "application/json")),
            @ApiResponse(responseCode = "404", description = "Session not found")
    })
    @DeleteMapping("/sessions/{sessionId}/users/{userId}")
    public SessionResource unAssignUserSession(@PathVariable Long sessionId, @PathVariable Long userId) {
        return convertToResource(sessionService.unAssignUserSession(userId, sessionId));
    }

    @Operation(summary = "Get Sessions", description = "Get Sessions by user Id", tags = {"Sessions"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Session returned", content = @Content(mediaType = "application/json")),
            @ApiResponse(responseCode = "404", description = "Session not found")
    })
    @GetMapping("/users/{userId}/sessions")
    public Page<SessionResource> getAllSessionsByUserId(@PathVariable Long userId, Pageable pageable) {
        Page<Session> sessionsPage = sessionService.getAllSessionsByUserId(userId, pageable);
        List<SessionResource> resources = sessionsPage.getContent().stream()
                .map(this::convertToResource).collect(Collectors.toList());
        return new PageImpl<>(resources, pageable, resources.size());
    }

    private Session convertToEntity(SaveSessionResource resource) {
        return mapper.map(resource, Session.class);
    }

    private SessionResource convertToResource(Session entity) {
        return mapper.map(entity, SessionResource.class);
    }
}
