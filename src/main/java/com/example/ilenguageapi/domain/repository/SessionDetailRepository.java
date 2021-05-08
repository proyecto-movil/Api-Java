package com.example.ilenguageapi.domain.repository;

import com.example.ilenguageapi.domain.model.SessionDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SessionDetailRepository  extends JpaRepository<SessionDetail, Long> {
    public Optional<SessionDetail> findByState(String state);
    public Optional<SessionDetail> findByIdAndSessionId(Long sessionId, Long sessionDetailId);
}
