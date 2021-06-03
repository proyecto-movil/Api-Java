package com.example.ilenguageapi.service;

import com.example.ilenguageapi.domain.model.LanguageOfInterest;
import com.example.ilenguageapi.domain.model.Role;
import com.example.ilenguageapi.domain.model.TopicOfInterest;
import com.example.ilenguageapi.domain.model.User;
import com.example.ilenguageapi.domain.repository.LanguageOfInterestRespository;
import com.example.ilenguageapi.domain.repository.RoleRepository;
import com.example.ilenguageapi.domain.repository.TopicOfInterestRepository;
import com.example.ilenguageapi.domain.repository.UserRepository;
import com.example.ilenguageapi.domain.service.UserService;
import com.example.ilenguageapi.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

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

    @Override
    public Page<User> getAllUsers(Pageable pageable) {
        return userRepository.findAll(pageable);
    }

    @Override
    public Page<User> getAllUsersByTopicIdAndRoleId(int topicId, int languageId, Pageable pageable) {
        return null;
    }

    @Override
    public Page<User> getAllUsersByRoleId(Long roleId, Pageable pageable) {
        return null;
    }

    @Override
    public Page<User> getAllUsersBySubscriptionId(Long subscriptionId, Pageable pageable) {
        return null;
    }

    @Override
    public User assignRoleById(User user, Long roleId) {
        Role role = roleRepository.findById(roleId).orElseThrow(() -> new ResourceNotFoundException("Role", "id", roleId));
        user.setRole(role);
        return user;
    }

    @Override
    public User assignTopicById(Long userId, Long topicId) {
        TopicOfInterest topic = topicOfInterestRepository.findById(topicId)
                .orElseThrow(() -> new ResourceNotFoundException("TopicOfInterest","Id",topicId));
        return userRepository.findById(userId).map(
                user -> userRepository.save(user.addTopicOfInterest(topic)))
                .orElseThrow(() -> new ResourceNotFoundException("User", "Id", userId));
    }

    @Override
    public User unassignTopicById(Long userId, Long topicId) {
        TopicOfInterest topic = topicOfInterestRepository.findById(topicId)
                .orElseThrow(() -> new ResourceNotFoundException("TopicOfInterest","Id",topicId));
        return userRepository.findById(userId).map(
                user -> userRepository.save(user.removeTopicOfInterest(topic)))
                .orElseThrow(() -> new ResourceNotFoundException("User", "Id", userId));
    }

    @Override
    public User assignLanguageById(Long userId, Long languageId) {
        LanguageOfInterest language = languageOfInterestRespository.findById(languageId)
                .orElseThrow(() -> new ResourceNotFoundException("LanguageOfInterest","Id",languageId));
        return userRepository.findById(userId).map(
                user -> userRepository.save(user.addLanguageOfInterest(language)))
                .orElseThrow(() -> new ResourceNotFoundException("User", "Id", userId));
    }

    @Override
    public User unassignLanguageById(Long userId, Long languageId) {
        LanguageOfInterest language = languageOfInterestRespository.findById(languageId)
                .orElseThrow(() -> new ResourceNotFoundException("LanguageOfInterest","Id",languageId));
        return userRepository.findById(userId).map(
                user -> userRepository.save(user.removeLanguageOfInterest(language)))
                .orElseThrow(() -> new ResourceNotFoundException("User", "Id", userId));
    }

    @Override
    public User getUserById(Long userId) {
        return userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User","id", userId));

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
                        .setProfilePhoto(userDetails.getProfilePhoto())
        );
    }

    @Override
    public ResponseEntity<?> deleteUser(Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User", "id", userId));
        userRepository.delete(user);
        return ResponseEntity.ok().build();
    }
}
