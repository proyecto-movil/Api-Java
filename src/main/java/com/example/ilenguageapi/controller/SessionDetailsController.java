package com.example.ilenguageapi.controller;

import com.example.ilenguageapi.domain.model.Session;
import com.example.ilenguageapi.domain.model.SessionDetail;
import com.example.ilenguageapi.domain.service.SessionDetailService;
import com.example.ilenguageapi.resource.SaveSessionDetailResource;
import com.example.ilenguageapi.resource.SaveSessionResource;
import com.example.ilenguageapi.resource.SessionDetailResource;
import com.example.ilenguageapi.resource.SessionResource;
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

   /* @Operation(summary = "Get Session Details", description = "Get All Session Details by Pages", tags = {"sessionDetails"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "All Session Details returned", content = @Content(mediaType = "application/json"))
    })
  @GetMapping("/sessions")
    public Page<SessionDetailResource> getAllSessionDetails(Pageable pageable) {
        Page<SessionDetail> sessionDetailsPage = sessionDetailService.getAllSessionDetails(pageable);
        List<SessionDetailResource> resources = sessionDetailsPage.getContent()
                .stream()
                .map(this::convertToResource)
                .collect(Collectors.toList());

        return new PageImpl<>(resources, pageable, resources.size());
    }*/

    @GetMapping("/session_details/{id}")
    public SessionDetailResource getSessionDetailById(@PathVariable(name = "id") Long sessionDetailId) {
        return convertToResource(sessionDetailService.getSessionDetailById(sessionDetailId));
    }

    @PostMapping("/session_details")
    public SessionDetailResource createSessionDetail(@Valid @RequestBody SaveSessionDetailResource resource) {
        SessionDetail sessionDetail = convertToEntity(resource);
        return convertToResource(sessionDetailService.createSessionDetail(sessionDetail));
    }

    @PutMapping("/sessionDetails/{sessionDetailsId}")
    public SessionDetailResource updateSessionDetail(@PathVariable Long sessionDetailId, @Valid @RequestBody SaveSessionDetailResource resource) {
        SessionDetail sessionDetail = convertToEntity(resource);
        return convertToResource(sessionDetailService.updateSessionDetail(sessionDetailId, sessionDetail));
    }

    @DeleteMapping("/sessionDetails/{sessionDetailsId}")
    public ResponseEntity<?> deleteSessionDetail(@PathVariable Long sessionDetailId) {
        return sessionDetailService.deleteSessionDetail(sessionDetailId);
    }


    private SessionDetail convertToEntity(SaveSessionDetailResource resource) {
        return mapper.map(resource, SessionDetail.class);
    }

    private SessionDetailResource convertToResource(SessionDetail entity) {
        return mapper.map(entity, SessionDetailResource.class);
    }
}
