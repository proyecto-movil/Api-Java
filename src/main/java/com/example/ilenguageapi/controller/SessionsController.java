package com.example.ilenguageapi.controller;

import com.example.ilenguageapi.domain.model.Session;
import com.example.ilenguageapi.domain.service.SessionService;
import com.example.ilenguageapi.resource.SaveSessionResource;
import com.example.ilenguageapi.resource.SessionResource;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
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

    @Operation(summary = "Get Sessions", description = "Get All Sessions by Pages", tags = {"sessions"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "All Sessions returned", content = @Content(mediaType = "application/json"))
    })
    @GetMapping("/sessions")
    public Page<SessionResource> getAllSessions(Pageable pageable) {
        Page<Session> sessionsPage = sessionService.getAllSessions(pageable);
        List<SessionResource> resources = sessionsPage.getContent()
                .stream()
                .map(this::convertToResource)
                .collect(Collectors.toList());

        return new PageImpl<>(resources, pageable, resources.size());
    }

    @GetMapping("/sessions/{id}")
    public SessionResource getSessionById(@PathVariable(name = "id") Long sessionId) {
        return convertToResource(sessionService.getSessionById(sessionId));
    }

    @PostMapping("/sessions")
    public SessionResource createSession(@Valid @RequestBody SaveSessionResource resource) {
        Session session = convertToEntity(resource);
        return convertToResource(sessionService.createSession(session));
    }

    @PutMapping("/sessions/{sessionId}")
    public SessionResource updateSession(@PathVariable Long sessionId, @Valid @RequestBody SaveSessionResource resource) {
        Session session = convertToEntity(resource);
        return convertToResource(sessionService.updateSession(sessionId, session));
    }

    @DeleteMapping("/sessions/{sessionId}")
    public ResponseEntity<?> deleteSession(@PathVariable Long sessionId) {
        return sessionService.deleteSession(sessionId);
    }


    private Session convertToEntity(SaveSessionResource resource) {
        return mapper.map(resource, Session.class);
    }

    private SessionResource convertToResource(Session entity) {
        return mapper.map(entity, SessionResource.class);
    }

}
