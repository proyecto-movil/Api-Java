package com.example.ilenguageapi.domain.repository;

import com.example.ilenguageapi.domain.model.Session;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.Optional;

@Repository
public interface SessionRepository extends JpaRepository<Session, Long> {
    public Optional<Session> findByStartAt(LocalDate startAt);
    public Optional<Session> findByEndAt(LocalDate endAt);
    public Optional<Session> findByState(String state);
    public Optional<Session> findByTopic(String topic);


}
