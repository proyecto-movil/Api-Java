package com.example.ilenguageapi.service;

import com.example.ilenguageapi.domain.model.TopicOfInterest;
import com.example.ilenguageapi.domain.repository.TopicOfInterestRepository;
import com.example.ilenguageapi.domain.repository.UserRepository;
import com.example.ilenguageapi.domain.service.TopicOfInterestService;
import com.example.ilenguageapi.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.http.ResponseEntity;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class TopicOfInterestServiceImpl implements TopicOfInterestService {
    @Autowired
    private TopicOfInterestRepository topicOfInterestRepository;

    @Autowired
    private UserRepository userRepository;

    @Override
    public Page<TopicOfInterest> getAllTopics(Pageable pageable) {
        return topicOfInterestRepository.findAll(pageable);
    }

    @Override
    public Page<TopicOfInterest> getAllTopicsByUserId(Long userId, Pageable pageable) {
        return userRepository.findById(userId).map(user -> {
            List<TopicOfInterest> topics = user.getTopicOfInterests();
            int userCount = topics.size();
            return new PageImpl<>(topics, pageable, userCount);
        }).orElseThrow(() -> new ResourceNotFoundException("User","Id",userId));
    }

    @Override
    public TopicOfInterest getTopicById(Long topicId) {
        return topicOfInterestRepository.findById(topicId)
                .orElseThrow(() -> new ResourceNotFoundException("TopicsOfInterest","Id",topicId));
    }

    @Override
    public TopicOfInterest createTopic(TopicOfInterest Topic) {
        return topicOfInterestRepository.save(Topic);
    }

    @Override
    public TopicOfInterest updateTopic(Long topicId, TopicOfInterest topicDetails) {
        TopicOfInterest topic = topicOfInterestRepository.findById(topicId)
                .orElseThrow(() -> new ResourceNotFoundException("TopicOfInterest","Id",topicId));
        return topicOfInterestRepository.save(
                topic.setTopicName(topicDetails.getTopicName())
        );
    }

    @Override
    public ResponseEntity<?> deleteTopic(Long topicId) {
        TopicOfInterest topic = topicOfInterestRepository.findById(topicId)
                .orElseThrow(()->new ResourceNotFoundException("TopicOfInterest","Id",topicId));
        topicOfInterestRepository.delete(topic);
        return ResponseEntity.ok().build();
    }
}
