package com.example.ilenguageapi.domain.service;

import com.example.ilenguageapi.domain.model.Role;
import com.example.ilenguageapi.domain.model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

public interface UserService {
    Page<User> getAllUsers(Pageable pageable);
    Page<User> getAllUsersByTopicIdAndLanguageId(Long topicId, Long languageId,Pageable pageable);
    Page<User> getAllTuthorsByTopicIdAndLanguageId(Long topicId, Long languageId,Pageable pageable);
    Page<User> getAllUsersByRoleId(Long roleId, Pageable pageable);
    Page<User> getAllUsersBySubscriptionId(Long subscriptionId, Pageable pageable);
    User assignRoleById(User user, Long roleId);
    User assignRoleByIdAndUserId(Long userId, Long roleId);

    User assignTopicById(Long userId, Long topicId);
    User unassignTopicById(Long userId, Long topicId);

    User assignLanguageById(Long userId, Long languageId);
    User unassignLanguageById(Long userId, Long languageId);

    User assignBadgetById(Long userId, Long badetId);
    User unassignBadgetById(Long userId, Long badgetId);

    User assignCommentById(Long tutorId, Long commentId);
    User unassignCommentById(Long tutorId, Long commentId);

    User getUserById(Long userId);
    User createUser(User user);
    User updateUser(Long userId, User userDetails);
    User updateMedia(Long userId);
    ResponseEntity<?> deleteUser(Long userId);

    Page<User> getAllUsersBySessionId(Long sessionId, Pageable pageable);
}