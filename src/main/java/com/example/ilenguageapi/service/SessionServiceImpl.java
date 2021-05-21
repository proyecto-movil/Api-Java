package com.example.ilenguageapi.service;

import com.example.ilenguageapi.domain.model.Session;
import com.example.ilenguageapi.domain.repository.SessionRepository;
import com.example.ilenguageapi.domain.service.SessionService;
import com.example.ilenguageapi.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;


@Service
public class SessionServiceImpl implements SessionService {

    @Autowired
    private SessionRepository sessionRepository;

    @Override
    public Page<Session> getAllSessions(Pageable pageable) {
        return sessionRepository.findAll(pageable);
    }

    @Override
    public Session getSessionById(Long sessionId) {
        return sessionRepository.findById(sessionId)
                .orElseThrow(() -> new ResourceNotFoundException("Session", "Id", sessionId));
    }

    @Override
    public Session createSession(Session session) {
        return sessionRepository.save(session);
    }

    @Override
    public Session updateSession(Long sessionId, Session sessionRequest) {
        Session session = sessionRepository.findById(sessionId)
                .orElseThrow(() -> new ResourceNotFoundException("Session", "Id", sessionId));

        return sessionRepository.save(
                session.setStartAt(sessionRequest.getStartAt())
                        .setEndAt(sessionRequest.getEndAt())
                        .setLink(sessionRequest.getLink()));
    }

    @Override
    public ResponseEntity<?> deleteSession(Long sessionId) {
        Session session = sessionRepository.findById(sessionId)
                .orElseThrow(() -> new ResourceNotFoundException("Session", "Id", sessionId));

        sessionRepository.delete(session);
        return ResponseEntity.ok().build();
    }

    @Override
    public Session getSessionByStartAt(String startAt) {
        return sessionRepository.findByStartAt(startAt)
                .orElseThrow(() -> new ResourceNotFoundException("Session", "StartAt", startAt));
    }

    @Override
    public Session getSessionByEndAt(String endAt) {
        return sessionRepository.findByEndAt(endAt)
                .orElseThrow(() -> new ResourceNotFoundException("Session", "EndAt", endAt));
    }
}
