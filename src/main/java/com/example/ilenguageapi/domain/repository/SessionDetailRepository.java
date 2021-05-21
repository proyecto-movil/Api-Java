package com.example.ilenguageapi.domain.repository;

import com.example.ilenguageapi.domain.model.SessionDetail;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SessionDetailRepository  extends JpaRepository<SessionDetail, Long> {
    Page<SessionDetail> findBySessionId(Long sessionId, Pageable pageable);
    Optional<SessionDetail> findByIdAndSessionId(Long id, Long sessionId);
    public Optional<SessionDetail> findByState(String state);
}
