package com.example.ilenguageapi.service;

import com.example.ilenguageapi.domain.model.Comment;
import com.example.ilenguageapi.domain.model.User;
import com.example.ilenguageapi.domain.repository.CommentRepository;
import com.example.ilenguageapi.domain.repository.UserRepository;
import com.example.ilenguageapi.domain.service.CommentService;
import com.example.ilenguageapi.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommentServiceImpl implements CommentService {
    @Autowired
    private CommentRepository commentRepository;
    @Autowired
    private UserRepository userRepository;

    @Override
    public Page<Comment> getAllComments(Pageable pageable) {
        return commentRepository.findAll(pageable);
    }

    @Override
    public Page<Comment> getAllCommentsByUserId(Long userId, Pageable pageable) {
        return userRepository.findById(userId).map(user -> {
            List<Comment> comments = user.getComments();
            int commentCount = comments.size();
            return new PageImpl<>(comments, pageable, commentCount);
        }).orElseThrow(() -> new ResourceNotFoundException("User", "Id", userId));
    }

    @Override
    public Comment getCommentById(Long commentId) {
        return commentRepository.findById(commentId)
                .orElseThrow(() -> new ResourceNotFoundException("Comment", "Id", commentId));
    }

    @Override
    public Comment createComment(Comment comment) {
        return commentRepository.save(comment);
    }

    @Override
    public Comment updateComment(Long commentId, Comment commentDetails) {
        Comment comment = commentRepository.findById(commentId)
                .orElseThrow(() -> new ResourceNotFoundException("Comment", "Id", commentId));
        return commentRepository.save(
                comment.setContent(commentDetails.getContent())
                        .setRating(commentDetails.getRating())
        );
    }

    @Override
    public ResponseEntity<?> deleteComment(Long commentId) {
        Comment comment = commentRepository.findById(commentId)
                .orElseThrow(() -> new ResourceNotFoundException("Comment", "Id", commentId));
        commentRepository.delete(comment);
        return ResponseEntity.ok().build();
    }
}
