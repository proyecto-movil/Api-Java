package com.example.ilenguageapi.service;

import com.example.ilenguageapi.domain.model.Session;
import com.example.ilenguageapi.domain.repository.SessionRepository;
import com.example.ilenguageapi.domain.repository.UserRepository;
import com.example.ilenguageapi.domain.service.SessionService;
import com.example.ilenguageapi.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

import java.util.List;

public class SessionServiceImpl implements SessionService {

    @Autowired
    private SessionRepository sessionRepository;
    @Autowired
    private UserRepository userRepository;

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
    public Page<Session> getAllSessionsByUserId(Long userId, Pageable pageable) {
        /*return userRepository.findById(userId).map(user -> {
            List<Session> sessions = user.getSession
        });*/
        //TODO: add a function to user model to get sessions
        return null;
    }

    @Override
    public Session getSessionByStartAt(String startAt) {
        return sessionRepository.findByStartAt(startAt)
                .orElseThrow(() -> new ResourceNotFoundException("Session", "StartAt", startAt));
    }
    //TODO: add getSessionByEndAt
}
