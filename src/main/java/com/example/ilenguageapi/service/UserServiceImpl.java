package com.example.ilenguageapi.service;

import com.example.ilenguageapi.domain.model.*;
import com.example.ilenguageapi.domain.repository.*;
import com.example.ilenguageapi.domain.service.UserService;
import com.example.ilenguageapi.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private TopicOfInterestRepository topicOfInterestRepository;
    @Autowired
    private LanguageOfInterestRespository languageOfInterestRespository;
    @Autowired
    private SessionRepository sessionRepository;
    @Autowired
    private BadgetRepository badgetRepository;
    @Autowired
    private CommentRepository commentRepository;

    @Override
    public Page<User> getAllUsers(Pageable pageable) {
        Page<User> users = userRepository.findAll(pageable);
        return users;
    }

    @Override
    public Page<User> getAllUsersByTopicIdAndLanguageId(Long topicId, Long languageId, Pageable pageable) {

        TopicOfInterest topic = topicOfInterestRepository.findById(topicId)
                .orElseThrow(() -> new ResourceNotFoundException("TopicOfInterest", "Id", topicId));

        LanguageOfInterest language = languageOfInterestRespository.findById(languageId)
                .orElseThrow(() -> new ResourceNotFoundException("LanguageOfInterest", "Id", languageId));

        List<User> usersFilter = userRepository.findAll(pageable)
                .stream()
                .filter(user -> user.hasTheTopicOf(topic) && user.hasTheLenguageOf(language))
                .collect(Collectors.toList());
        return new PageImpl<>(usersFilter, pageable, usersFilter.size());
    }

    @Override
    public Page<User> getAllTuthorsByTopicIdAndLanguageId(Long topicId, Long languageId, Pageable pageable) {
        TopicOfInterest topic = topicOfInterestRepository.findById(topicId)
                .orElseThrow(() -> new ResourceNotFoundException("TopicOfInterest", "Id", topicId));

        LanguageOfInterest language = languageOfInterestRespository.findById(languageId)
                .orElseThrow(() -> new ResourceNotFoundException("LanguageOfInterest", "Id", languageId));
        Role role = roleRepository.findByName("Tuthor").orElseThrow(() -> new ResourceNotFoundException("Role", "Name", "Tuthor"));
        List<User> usersFilter = userRepository.findAllByRole(role, pageable)
                .stream()
                .filter(user -> user.hasTheTopicOf(topic) && user.hasTheLenguageOf(language) && user.isUserWithRole("Tuthor"))
                .collect(Collectors.toList());
        return new PageImpl<>(usersFilter, pageable, usersFilter.size());
    }

    @Override
    public Page<User> getAllUsersByRoleId(Long roleId, Pageable pageable) {
        Role role = roleRepository.findById(roleId).orElseThrow(() -> new ResourceNotFoundException("Role", "Id", roleId));
        return userRepository.findAllByRole(role, pageable);
    }

    @Override
    public Page<User> getAllUsersBySubscriptionId(Long subscriptionId, Pageable pageable) {
        return null;
    }

    @Override
    public User assignRoleById(User user, Long roleId) {
        Role role = roleRepository.findById(roleId).orElseThrow(() -> new ResourceNotFoundException("Role", "id", roleId));
        User userWithRole = user.setRole(role);
        //TODO: cambiado para que solo saque al usuario
        return user;
    }

    @Override
    public User assignRoleByIdAndUserId(Long userId, Long roleId) {
        Role role = roleRepository.findById(roleId)
                .orElseThrow(() -> new ResourceNotFoundException("Role", "Id", roleId));
        return userRepository.findById(userId).map(
                user -> userRepository.save(user.setRole(role.addUser(user))))
                .orElseThrow(() -> new ResourceNotFoundException("User", "Id", userId));
    }

    @Override
    public User assignTopicById(Long userId, Long topicId) {
        TopicOfInterest topic = topicOfInterestRepository.findById(topicId)
                .orElseThrow(() -> new ResourceNotFoundException("TopicOfInterest", "Id", topicId));
        return userRepository.findById(userId).map(
                user -> userRepository.save(user.addTopicOfInterest(topic)))
                .orElseThrow(() -> new ResourceNotFoundException("User", "Id", userId));
    }

    @Override
    public User unassignTopicById(Long userId, Long topicId) {
        TopicOfInterest topic = topicOfInterestRepository.findById(topicId)
                .orElseThrow(() -> new ResourceNotFoundException("TopicOfInterest", "Id", topicId));
        return userRepository.findById(userId).map(
                user -> userRepository.save(user.removeTopicOfInterest(topic)))
                .orElseThrow(() -> new ResourceNotFoundException("User", "Id", userId));
    }

    @Override
    public User assignLanguageById(Long userId, Long languageId) {
        LanguageOfInterest language = languageOfInterestRespository.findById(languageId)
                .orElseThrow(() -> new ResourceNotFoundException("LanguageOfInterest", "Id", languageId));
        return userRepository.findById(userId).map(
                user -> userRepository.save(user.addLanguageOfInterest(language)))
                .orElseThrow(() -> new ResourceNotFoundException("User", "Id", userId));
    }

    @Override
    public User unassignLanguageById(Long userId, Long languageId) {
        LanguageOfInterest language = languageOfInterestRespository.findById(languageId)
                .orElseThrow(() -> new ResourceNotFoundException("LanguageOfInterest", "Id", languageId));
        return userRepository.findById(userId).map(
                user -> userRepository.save(user.removeLanguageOfInterest(language)))
                .orElseThrow(() -> new ResourceNotFoundException("User", "Id", userId));
    }

    public User assignBadgetById(Long userId, Long badgetId) {
        Badget badget = badgetRepository.findById(badgetId)
                .orElseThrow(() -> new ResourceNotFoundException("Badget", "Id", badgetId));
        return userRepository.findById(userId).map(
                user -> userRepository.save(user.addBadget(badget)))
                .orElseThrow(() -> new ResourceNotFoundException("User", "Id", userId));
    }

    public User unassignBadgetById(Long userId, Long badgetId) {
        Badget badget = badgetRepository.findById(badgetId)
                .orElseThrow(() -> new ResourceNotFoundException("Badget", "Id", badgetId));
        return userRepository.findById(userId).map(
                user -> userRepository.save(user.removeBadget(badget)))
                .orElseThrow(() -> new ResourceNotFoundException("User", "Id", userId));
    }

    @Override
    public User assignCommentById(Long tutorId, Long commentId) {
        Comment comment = commentRepository.findById(commentId)
                .orElseThrow(() -> new ResourceNotFoundException("Comment", "Id", commentId));
        comment.setUser(userRepository.findById(tutorId).orElseThrow(() -> new ResourceNotFoundException("User", "Id", tutorId)));
        return userRepository.findById(tutorId).map(
                user -> userRepository.save(user.addComment(comment)))
                .orElseThrow(() -> new ResourceNotFoundException("User", "Id", tutorId));
    }

    @Override
    public User unassignCommentById(Long tutorId, Long commentId) {
        Comment comment = commentRepository.findById(commentId)
                .orElseThrow(() -> new ResourceNotFoundException("Comment", "Id", commentId));
        comment.setUser(null);
        return userRepository.findById(tutorId).map(
                user -> userRepository.save(user.removeComment(comment)))
                .orElseThrow(() -> new ResourceNotFoundException("User", "Id", tutorId));
    }

    @Override
    public User getUserById(Long userId) {
        return userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User", "id", userId));

    }

    @Override
    public User createUser(User user) {
        return userRepository.save(user);
    }

    @Override
    public User updateUser(Long userId, User userDetails) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User", "id", userId));
        return userRepository.save(
                user.setName(userDetails.getName())
                        .setLastName(userDetails.getLastName())
                        .setEmail(userDetails.getEmail())
                        .setPassword(userDetails.getPassword())
                        .setDescription(userDetails.getDescription())
                        .setMedia(userDetails.getMedia())
        );
    }

    @Override
    public User updateMedia(Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User", "id", userId));
        return userRepository.save(
                user.setMedia(user.getRatingMedia())
        );
    }

    @Override
    public ResponseEntity<?> deleteUser(Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User", "id", userId));
        userRepository.delete(user);
        return ResponseEntity.ok().build();
    }

    @Override
    public Page<User> getAllUsersBySessionId(Long sessionId, Pageable pageable) {
        return sessionRepository.findById(sessionId).map(session -> {
            List<User> users = session.getUsers();
            int usersCount = users.size();
            return new PageImpl<>(users, pageable, usersCount);
        }).orElseThrow(() -> new ResourceNotFoundException("Sessions", "Id", sessionId));
    }
}
