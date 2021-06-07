package com.example.ilenguageapi.controller;

import com.example.ilenguageapi.domain.model.SessionDetail;
import com.example.ilenguageapi.domain.service.SessionDetailService;
import com.example.ilenguageapi.resource.SaveSessionDetailResource;
import com.example.ilenguageapi.resource.SessionDetailResource;
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
public class SessionDetailsController {
    @Autowired
    private SessionDetailService sessionDetailService;
    @Autowired
    private ModelMapper mapper;

    @Operation(summary = "Get SessionDetails by SessionId", description = "Get All SessionDetails by SessionId by Pages", tags = {"SessionDetails"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "All SessionDetails returned", content = @Content(mediaType = "application/json"))
    })
    @GetMapping("/sessions/{sessionId}/session-details")
    public Page<SessionDetailResource> getAllSessionDetailsBySessionId(@PathVariable Long sessionId, Pageable pageable) {
        Page<SessionDetail> sessionDetailPage = sessionDetailService.getAllSessionDetailsBySessionId(sessionId, pageable);
        List<SessionDetailResource> resources = sessionDetailPage.getContent().stream().map(
                this::convertToResource).collect(Collectors.toList());
        return new PageImpl<>(resources, pageable, resources.size());
    }

    @Operation(summary = "Get SessionDetails by Id and by SessionId", description = "Get All SessionDetails by Id and by SessionId by Pages", tags = {"SessionDetails"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "All SessionDetails returned", content = @Content(mediaType = "application/json"))
    })
    @GetMapping("/sessions/{sessionId}/session-details/{sessionDetailId}")
    public SessionDetailResource getSessionDetailByIdAndSessionId(@PathVariable Long sessionId, @PathVariable Long sessionDetailId) {
        return convertToResource(sessionDetailService.getSessionDetailByIdAndSessionId(sessionId, sessionDetailId));
    }

    @Operation(summary = "Add SessionDetail", description = "Create new sessionDetail", tags = {"SessionDetails"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Session Detail created", content = @Content(mediaType = "application/json")),
    })
    @PostMapping("/sessions/{sessionId}/session-details")
    public SessionDetailResource createSessionDetail(
            @PathVariable Long sessionId,
            @Valid @RequestBody SaveSessionDetailResource resource) {
        return convertToResource(sessionDetailService.createSessionDetail(sessionId, convertToEntity(resource)));
    }

    @Operation(summary = "Update SessionDetail", description = "Update sessionDetail by sessionDetail", tags = {"SessionDetails"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Session Detail Updated", content = @Content(mediaType = "application/json")),
    })
    @PutMapping("/sessions/{sessionId}/session-details/{sessionDetailId}")
    public SessionDetailResource updateSessionDetail(
            @PathVariable Long sessionId,
            @PathVariable Long sessionDetailId,
            @Valid @RequestBody SaveSessionDetailResource resource) {
        return convertToResource(sessionDetailService.updateSessionDetail(sessionId, sessionDetailId, convertToEntity(resource)));
    }

    @Operation(summary = "Delete SessionDetail", description = "Deleted sessionDetail by sessionDetailId", tags = {"SessionDetails"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "User deleted", content = @Content(mediaType = "application/json")),
    })
    @DeleteMapping("/sessions/{sessionId}/session-details/{sessionDetailId}")
    public ResponseEntity<?> deleteSessionDetail(
            @PathVariable Long sessionId,
            @PathVariable Long sessionDetailId) {
        return sessionDetailService.deleteSessionDetail(sessionId, sessionDetailId);
    }

    private SessionDetail convertToEntity(SaveSessionDetailResource resource) {
        return mapper.map(resource, SessionDetail.class);
    }

    private SessionDetailResource convertToResource(SessionDetail entity) {
        return mapper.map(entity, SessionDetailResource.class);
    }
}
