package com.example.ilenguageapi.service;

import com.example.ilenguageapi.domain.model.*;
import com.example.ilenguageapi.domain.repository.SessionRepository;
import com.example.ilenguageapi.domain.repository.UserRepository;
import com.example.ilenguageapi.domain.service.SessionService;
import com.example.ilenguageapi.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
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
    public Session getSessionByStartAt(LocalDate startAt) {
        return sessionRepository.findByStartAt(startAt)
                .orElseThrow(() -> new ResourceNotFoundException("Session", "StartAt", startAt));
    }

    @Override
    public Session getSessionByEndAt(LocalDate endAt) {
        return sessionRepository.findByEndAt(endAt)
                .orElseThrow(() -> new ResourceNotFoundException("Session", "EndAt", endAt));
    }

    @Override
    public Session getSessionByState(String state) {
        return sessionRepository.findByState(state)
                .orElseThrow(() -> new ResourceNotFoundException("Session", "State", state));
    }

    @Override
    public Session getSessionByTopic(String topic) {
        return sessionRepository.findByTopic(topic)
                .orElseThrow(() -> new ResourceNotFoundException("Session", "Topic", topic));
    }

    @Override
    public Session assignUserSession(Long userId, Long sessionId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User", "Id", userId));
        return sessionRepository.findById(sessionId).map(
                session -> sessionRepository.save(session.userWith(user)))
                .orElseThrow(() -> new ResourceNotFoundException("Session", "Id", sessionId));
    }

    @Override
    public Session unAssignUserSession(Long userId, Long sessionId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User", "Id", userId));
        return sessionRepository.findById(sessionId).map(
                session -> sessionRepository.save(session.unUserWith(user)))
                .orElseThrow(() -> new ResourceNotFoundException("Session", "Id", sessionId));
    }

    @Override
    public Page<Session> getAllSessionsByUserId(Long userId, Pageable pageable) {
        return userRepository.findById(userId).map(user -> {
            List<Session> sessions = user.getSessions();
            int sessionsCount = sessions.size();
            return new PageImpl<>(sessions, pageable, sessionsCount); })
                .orElseThrow(() -> new ResourceNotFoundException("User", "Id", userId));
    }

    @Override
    public Page<Session> getSessionsByUserIdAndTutorId(Long userId, Long tutorId, Pageable pageable) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User", "Id", userId));

        User tutor = userRepository.findById(tutorId)
                .orElseThrow(() -> new ResourceNotFoundException("Tutor", "Id", userId));

        List<Session> sessionsFilter = sessionRepository.findAll(pageable)
                .stream()
                .filter(session -> session.isRelatedWith(user) && session.isRelatedWith(tutor))
                .collect(Collectors.toList());
        int sessionsCount = sessionsFilter.size();
        return new PageImpl<>(sessionsFilter,pageable,sessionsCount);
    }


}
