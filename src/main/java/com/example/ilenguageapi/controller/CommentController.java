package com.example.ilenguageapi.controller;

import com.example.ilenguageapi.domain.model.Badget;
import com.example.ilenguageapi.domain.model.Comment;
import com.example.ilenguageapi.domain.service.CommentService;
import com.example.ilenguageapi.resource.CommentResource;
import com.example.ilenguageapi.resource.SaveCommentResource;
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
public class CommentController {
    @Autowired
    private CommentService commentService;
    @Autowired
    private ModelMapper modelMapper;

    private Comment converToEntity(SaveCommentResource resource) {
        return modelMapper.map(resource, Comment.class);
    }
    private CommentResource convertToResource(Comment entity){
        return modelMapper.map(entity,CommentResource.class);
    }
    @Operation(summary = "Add Comment", description = "Create new comment", tags = {"comments"})
    @PostMapping("/comments")
    public CommentResource createComment(@Valid @RequestBody SaveCommentResource resource){
        Comment comment = converToEntity(resource);
        return convertToResource(commentService.createComment(comment));
    }

    @Operation(summary = "Get all comments", description = "Get all comments by pages", tags = {"comments"})
    @ApiResponses( value = {
            @ApiResponse(responseCode = "200", description = "All comments returned", content = @Content(mediaType = "application/json")),
            @ApiResponse(responseCode = "404", description = "Comments not found")
    })
    @GetMapping("/comments")
    public Page<CommentResource> getAllBadgets(Pageable pageable) {
        Page<Comment> commentPage = commentService.getAllComments(pageable);
        List<CommentResource> resources = commentPage.getContent()
                .stream()
                .map(this::convertToResource)
                .collect(Collectors.toList());
        return new PageImpl<>(resources, pageable, resources.size());
    }
    @Operation(summary = "Delete comment", description = "Deleted comment by commentId", tags = {"comments"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Comment deleted", content = @Content(mediaType = "application/json"))
    })
    @DeleteMapping("/comments/{commentId}")
    public ResponseEntity<?> deleteComment(@PathVariable Long commentId){
        return commentService.deleteComment(commentId);
    }
}
