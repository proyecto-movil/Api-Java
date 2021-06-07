package com.example.ilenguageapi.service;

import com.example.ilenguageapi.domain.model.SessionDetail;
import com.example.ilenguageapi.domain.repository.SessionDetailRepository;
import com.example.ilenguageapi.domain.repository.SessionRepository;
import com.example.ilenguageapi.domain.service.SessionDetailService;
import com.example.ilenguageapi.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class SessionDetailServiceImpl implements SessionDetailService {

    @Autowired
    private SessionRepository sessionRepository;

    @Autowired
    private SessionDetailRepository sessionDetailRepository;

    @Override
    public Page<SessionDetail> getAllSessionDetailsBySessionId(Long sessionId, Pageable pageable) {
        return sessionDetailRepository.findBySessionId(sessionId, pageable);
    }

    @Override
    public SessionDetail getSessionDetailByIdAndSessionId(Long sessionId, Long sessionDetailId) {
        return sessionDetailRepository.findByIdAndSessionId(sessionDetailId, sessionId)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "SessionDetail not found with Id" + sessionDetailId +
                                " and SessionId " + sessionId));
    }

    @Override
    public SessionDetail createSessionDetail(Long sessionId, SessionDetail sessionDetail) {
        return sessionRepository.findById(sessionId).map(session -> {
            sessionDetail.setSession(session);
            return sessionDetailRepository.save(sessionDetail);
        }).orElseThrow(() -> new ResourceNotFoundException(
                "Session", "Id", sessionId));
    }

    @Override
    public SessionDetail updateSessionDetail(Long sessionId, Long sessionDetailId, SessionDetail sessionDetailDetails) {
        if(!sessionRepository.existsById(sessionId))
            throw new ResourceNotFoundException("Session", "Id", sessionId);

        return sessionDetailRepository.findById(sessionDetailId).map(sessionDetail -> {
            sessionDetail.setState(sessionDetailDetails.getState());
            return sessionDetailRepository.save(sessionDetail);
        }).orElseThrow(() -> new ResourceNotFoundException(
                "SessionDetail", "Id", sessionDetailId));
    }

    @Override
    public ResponseEntity<?> deleteSessionDetail(Long sessionId, Long sessionDetailId) {
        if(!sessionRepository.existsById(sessionId))
            throw new ResourceNotFoundException("Session", "Id", sessionId);

        return sessionDetailRepository.findById(sessionDetailId).map(sessionDetail -> {
            sessionDetailRepository.delete(sessionDetail);
            return ResponseEntity.ok().build();
        }).orElseThrow(() -> new ResourceNotFoundException(
                "SessionDetail", "Id", sessionDetailId));
    }

    @Override
    public SessionDetail getSessionDetailByState(String state) {
        return sessionDetailRepository.findByState(state)
                .orElseThrow(() -> new ResourceNotFoundException("SessionDetail", "State", state));
    }
}
