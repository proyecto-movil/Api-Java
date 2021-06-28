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
    Session assignUserSession(Long userId, Long sessionId);
    Session unAssignUserSession(Long userId, Long sessionId);
    Page<Session> getAllSessionsByUserId(Long userId, Pageable pageable);
    Page<Session> getSessionsByUserIdAndTutorId(Long userId, Long tutorId, Pageable pageable);
}
