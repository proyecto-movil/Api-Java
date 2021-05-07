package com.example.ilenguageapi.domain.service;

import com.example.ilenguageapi.domain.model.Session;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

public interface SessionService {
    Page<Session> getAllSessions(Pageable pageable);
    Session getSessionById(Long sessionId);
    Session createSession(Session session);
    Session updateSession(Long sessionId, Session sessionRequest);
    ResponseEntity<?> deleteSession(Long sessionId);
    Page<Session> getAllSessionsByUserId(Long userId, Pageable pageable);
    Session getSessionByStartAt(String startAt);
    Session getSessionByEndAt(String endAt);
}
