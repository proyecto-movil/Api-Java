package com.example.ilenguageapi.domain.repository;

import com.example.ilenguageapi.domain.model.TopicOfInterest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TopicOfInterestRepository extends JpaRepository<TopicOfInterest, Long> {
}
