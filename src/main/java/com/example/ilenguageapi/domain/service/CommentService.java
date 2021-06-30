package com.example.ilenguageapi.domain.service;

import com.example.ilenguageapi.domain.model.Comment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

public interface CommentService {
    Page<Comment> getAllComments(Pageable pageable);
    Page<Comment> getAllCommentsByUserId(Long userId, Pageable pageable);
    Comment getCommentById(Long commentId);
    Comment createComment(Comment comment);
    Comment updateComment(Long commentId, Comment commentDetails);
    ResponseEntity<?> deleteComment(Long commentId);
}
