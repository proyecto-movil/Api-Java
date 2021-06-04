package com.example.ilenguageapi.domain.repository;

import com.example.ilenguageapi.domain.model.Session;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SessionRepository extends JpaRepository<Session, Long> {
    public Optional<Session> findByStartAt(String startAt);
    public Optional<Session> findByEndAt(String endAt);
    public Optional<Session> findByState(String state);
    public Optional<Session> findByTopic(String topic);
    Page<Session> findByScheduleId(int scheduleId, Pageable pageable);
    Optional<Session> findByIdAndScheduleId(Long id, int scheduleId);

}
