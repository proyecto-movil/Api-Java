package com.example.ilenguageapi.domain.repository;

import com.example.ilenguageapi.domain.model.Session;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface SessionRepository extends JpaRepository<Session, Long> {
    public Optional<Session> findByStartAt(String title);
}
