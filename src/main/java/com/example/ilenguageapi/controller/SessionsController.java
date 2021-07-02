package com.example.ilenguageapi.controller;

import com.example.ilenguageapi.domain.model.Session;
import com.example.ilenguageapi.domain.model.User;
import com.example.ilenguageapi.domain.model.UserSubscription;
import com.example.ilenguageapi.domain.service.SessionService;
import com.example.ilenguageapi.resource.SaveSessionResource;
import com.example.ilenguageapi.resource.SessionResource;
import com.example.ilenguageapi.resource.UserResource;
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
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api")
public class SessionsController {

    @Autowired
    private SessionService sessionService;

    @Autowired
    private ModelMapper mapper;

    @Operation(summary = "Get Sessions", description = "Get All Sessions by Pages", tags = {"Sessions"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "All Sessions returned", content = @Content(mediaType = "application/json"))
    })
    @GetMapping("/sessions")
    public Page<SessionResource> getAllSessions(Pageable pageable) {
        Page<Session> sessionPage = sessionService.getAllSessions(pageable);
        List<SessionResource> resources = sessionPage.getContent()
                .stream()
                .map(this::convertToResource)
                .collect(Collectors.toList());

        return new PageImpl<>(resources, pageable, resources.size());
    }

    @Operation(summary = "Get Session", description = "Get Session by sessionId", tags = {"Sessions"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Session returned", content = @Content(mediaType = "application/json")),
            @ApiResponse(responseCode = "404", description = "Session not found")
    })
    @GetMapping("/sessions/{sessionId}")
    public SessionResource getSessionById(@PathVariable Long sessionId) {
        return convertToResource(sessionService.getSessionById(sessionId));
    }

    @Operation(summary = "Add Session", description = "Create new session", tags = {"Sessions"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Session created", content = @Content(mediaType = "application/json")),
    })
    @PostMapping("/sessions")
    public SessionResource createSession(@Valid @RequestBody SaveSessionResource resource) {
        Session session = convertToEntity(resource);
        return convertToResource(sessionService.createSession(session));
    }

    @Operation(summary = "Update Session", description = "Update session by sessionId", tags = {"Sessions"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Session Updated", content = @Content(mediaType = "application/json")),
    })
    @PutMapping("/sessions/{sessionId}")
    public SessionResource updateSession(@PathVariable Long sessionId, @Valid @RequestBody SaveSessionResource resource) {
        Session session = convertToEntity(resource);
        return convertToResource(sessionService.updateSession(sessionId, session));
    }

    @Operation(summary = "Delete Session", description = "Deleted session by sessionId", tags = {"Sessions"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "User deleted", content = @Content(mediaType = "application/json")),
    })
    @DeleteMapping("/sessions/{sessionId}")
    public ResponseEntity<?> deleteSession(@PathVariable Long sessionId) {
        return sessionService.deleteSession(sessionId);
    }

    @Operation(summary = "List Sessions", description = "List sessions by user id and tutor id", tags = {"Sessions"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Returned all sessions", content = @Content(mediaType = "application/json")),
    })
    @GetMapping("/users/{userId}/tutors/{tutorId}/sessions")
    public Page<SessionResource> getAllSessionsByUserIdAndTutorId(@PathVariable Long userId, @PathVariable Long tutorId, Pageable pageable){
        Page<Session> sessionPage = sessionService.getSessionsByUserIdAndTutorId(userId, tutorId, pageable);
        List<SessionResource> resources = sessionPage.getContent()
                .stream()
                .map(this::convertToResource)
                .collect(Collectors.toList());
        return new PageImpl<>(resources, pageable, resources.size());
    }


    private Session convertToEntity(SaveSessionResource resource) {
        return mapper.map(resource, Session.class);
    }

    private SessionResource convertToResource(Session entity) {
        return mapper.map(entity, SessionResource.class);
    }

}
