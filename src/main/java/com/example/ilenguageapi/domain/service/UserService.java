package com.example.ilenguageapi.domain.service;

import com.example.ilenguageapi.domain.model.Role;
import com.example.ilenguageapi.domain.model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

public interface UserService {
    Page<User> getAllUsers(Pageable pageable);
    Page<User> getAllUsersByRoleId(Long roleId, Pageable pageable);
    Page<User> getAllUsersBySubscriptionId(Long subscriptionId, Pageable pageable);
    User assignRoleById(User user, Long roleId);

    User assignTopicById(Long userId, Long topicId);
    User unassignTopicById(Long userId, Long topicId);

    User assignLanguageById(Long userId, Long languageId);
    User unassignLanguageById(Long userId, Long languageId);

    User getUserById(Long userId);
    User createUser(User user);
    User updateUser(Long userId, User userDetails);
    ResponseEntity<?> deleteUser(Long userId);
}