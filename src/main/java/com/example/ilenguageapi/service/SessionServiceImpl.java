package com.example.ilenguageapi.service;

import com.example.ilenguageapi.domain.model.*;
import com.example.ilenguageapi.domain.repository.ScheduleRepository;
import com.example.ilenguageapi.domain.repository.SessionRepository;
import com.example.ilenguageapi.domain.service.SessionService;
import com.example.ilenguageapi.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class SessionServiceImpl implements SessionService {

    @Autowired
    private SessionRepository sessionRepository;

    @Autowired
     private ScheduleRepository scheduleRepository;

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
    public Page<Session> getAllSessionsByScheduleId(Long scheduleId, Pageable pageable) {
        return sessionRepository.findByScheduleId(scheduleId, pageable);
    }

    @Override
    public Session getSessionByIdAndScheduleId(Long scheduleId, Long sessionId) {
        return sessionRepository.findByIdAndScheduleId(sessionId, scheduleId)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Session not found with Id" + sessionId +
                                " and ScheduleId " + scheduleId));
    }

    @Override
    public Session assignSessionSchedule(Long sessionId, Long scheduleId) {
        Schedule schedule= scheduleRepository.findById(scheduleId)
                .orElseThrow(() -> new ResourceNotFoundException("Schedule", "Id", scheduleId));
        return sessionRepository.findById(sessionId).map(
                session -> sessionRepository.save(session.setSchedule(schedule)))
                .orElseThrow(() -> new ResourceNotFoundException("Session", "Id", sessionId));

    }



    @Override
    public ResponseEntity<?> deleteSession(int scheduleId, Long sessionId) {
        Optional<Schedule> foundSchedule = scheduleRepository.findById((long) scheduleId);
        if(foundSchedule.isEmpty())
            throw new ResourceNotFoundException("Schedule", "Id", scheduleId);

        return sessionRepository.findById(sessionId).map(session -> {
            sessionRepository.delete(session);
            return ResponseEntity.ok().build();
        }).orElseThrow(() -> new ResourceNotFoundException(
                "Session", "Id", sessionId));
    }
}
