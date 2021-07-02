package com.example.ilenguageapi.controller;


import com.example.ilenguageapi.domain.model.Comment;
import com.example.ilenguageapi.domain.model.User;
import com.example.ilenguageapi.domain.service.CommentService;
import com.example.ilenguageapi.domain.service.UserService;
import com.example.ilenguageapi.resource.CommentResource;
import com.example.ilenguageapi.resource.SaveCommentResource;
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

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api")
public class UserCommentsController {
    @Autowired
    private UserService userService;
    @Autowired
    private CommentService commentService;
    @Autowired
    private ModelMapper modelMapper;
    private User convertToEntityUser(SaveUserResource resource) {
        return modelMapper.map(resource, User.class);
    }
    private UserResource convertToResourceUser(User entity) {
        return modelMapper.map(entity, UserResource.class);
    }
    private Comment convertToEntityComment (SaveCommentResource resource){
       return modelMapper.map(resource,Comment.class);
    }
    private CommentResource converToResoureComment(Comment entity){
       return modelMapper.map(entity,CommentResource.class);
    }

    @Operation(summary = "assing comment to tutor", description = "Assign comment to tutor by commentId and tutorId", tags = {"commentUsers"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "assigned comment", content = @Content(mediaType = "application/json")),
            @ApiResponse(responseCode = "404", description = "comment not found or User not found")
    })
    @PostMapping("/users/{tutorId}/comments/{commentId}")
    public UserResource assignCommentUser(
            @PathVariable Long tutorId,
            @PathVariable Long commentId){
       return convertToResourceUser(userService.assignCommentById(tutorId,commentId));
    }

    @Operation(summary = "unassing comment to tutor", description = "Unassign comment to tutor by commentId and tutorId", tags = {"commentUsers"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "unassigned comment", content = @Content(mediaType = "application/json")),
            @ApiResponse(responseCode = "404", description = "comment not found or User not found")
    })
    @DeleteMapping("/users/{tutorId}/comments/{commentId}")
    public UserResource unassignCommentUser(
            @PathVariable Long tutorId,
            @PathVariable Long commentId){
        return convertToResourceUser(userService.unassignCommentById(tutorId,commentId));
    }
    @Operation(summary = "GetAllCommentsByUserId", description = "Get all Comments by userId and pageable", tags = {"commentUsers"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "All comments returned", content = @Content(mediaType = "application/json")),
            @ApiResponse(responseCode = "404", description = "User not found")
    })
    @GetMapping("/users/{tutorId}/comments")
    public Page<CommentResource> getAllCommentsByUserd(
            @PathVariable Long tutorId,
            Pageable pageable){
       Page<Comment> commentPage = commentService.getAllCommentsByUserId(tutorId,pageable);
       List<CommentResource> resources = commentPage.getContent()
               .stream()
               .map(this::converToResoureComment)
               .collect(Collectors.toList());
       return new PageImpl<>(resources,pageable, resources.size());
    }
}
