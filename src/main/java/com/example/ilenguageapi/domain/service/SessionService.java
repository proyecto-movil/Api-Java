package com.example.ilenguageapi.domain.service;

import com.example.ilenguageapi.domain.model.Session;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

import java.time.LocalDate;

public interface SessionService {
    Page<Session> getAllSessions(Pageable pageable);
    Session getSessionById(Long sessionId);
    Session createSession(Session session);
    Session updateSession(Long sessionId, Session sessionRequest);
    ResponseEntity<?> deleteSession(Long sessionId);
    Session getSessionByStartAt(LocalDate startAt);
    Session getSessionByEndAt(LocalDate endAt);
    Session getSessionByState(String state);
    Session getSessionByTopic(String topic);
    Page<Session> getAllSessionsByScheduleId(int scheduleId, Pageable pageable);
    Session getSessionByIdAndScheduleId(int scheduleId, Long sessionId);
   // ResponseEntity<?> deleteSession(int scheduleId, Long sessionId);
}
