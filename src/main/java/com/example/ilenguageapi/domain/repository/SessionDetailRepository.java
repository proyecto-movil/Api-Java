package com.example.ilenguageapi.domain.repository;

import com.example.ilenguageapi.domain.model.Session;
import com.example.ilenguageapi.domain.model.SessionDetail;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface SessionDetailRepository  extends JpaRepository<SessionDetail, Long> {
    public Optional<SessionDetail> findByState(String state);
}
