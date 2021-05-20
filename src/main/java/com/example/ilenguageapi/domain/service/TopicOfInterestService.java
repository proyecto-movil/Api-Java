package com.example.ilenguageapi.domain.service;

import com.example.ilenguageapi.domain.model.TopicOfInterest;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.data.domain.Pageable;


public interface TopicOfInterestService {
    Page<TopicOfInterest> getAllTopics(Pageable pageable);
    Page<TopicOfInterest> getAllTopicsByUserId(Long userId, Pageable pageable);
    TopicOfInterest getTopicById(Long topicId);
    TopicOfInterest createTopic(TopicOfInterest Topic);
    TopicOfInterest updateTopic(Long topicId, TopicOfInterest topicDetails);
    ResponseEntity<?> deleteTopic(Long topicId);
}
