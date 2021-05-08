package com.example.ilenguageapi.service;

import com.example.ilenguageapi.domain.model.SessionDetail;
import com.example.ilenguageapi.domain.repository.SessionDetailRepository;
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
    private SessionDetailRepository sessionDetailRepository;
    //@Autowired
    //private SessionRepository sessionRepository;

    @Override
    public Page<SessionDetail> getAllSessionDetails(Pageable pageable) {
        return sessionDetailRepository.findAll(pageable);
    }

    @Override
    public SessionDetail getSessionDetailById(Long sessionDetailId) {
        return sessionDetailRepository.findById(sessionDetailId)
                .orElseThrow(() -> new ResourceNotFoundException("SessionDetail", "Id", sessionDetailId));
    }

    @Override
    public SessionDetail createSessionDetail(SessionDetail sessionDetail) {
        return sessionDetailRepository.save(sessionDetail);
    }

    @Override
    public SessionDetail updateSessionDetail(Long sessionDetailId, SessionDetail sessionDetailRequest) {
        SessionDetail sessionDetail = sessionDetailRepository.findById(sessionDetailId)
                .orElseThrow(() -> new ResourceNotFoundException("SessionDetail", "Id", sessionDetailId));

        return sessionDetailRepository.save(
                sessionDetail.setState(sessionDetailRequest.getState()));
    }

    @Override
    public ResponseEntity<?> deleteSessionDetail(Long sessionDetailId) {
        SessionDetail sessionDetail = sessionDetailRepository.findById(sessionDetailId)
                .orElseThrow(() -> new ResourceNotFoundException("SessionDetail", "Id", sessionDetailId));
        sessionDetailRepository.delete(sessionDetail);
        return ResponseEntity.ok().build();
    }

    @Override
    public SessionDetail getSessionDetailByIdAndSessionId(Long sessionId, Long sessionDetailId) {
        return sessionDetailRepository.findByIdAndSessionId(sessionId, sessionDetailId)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Session Detail not found with Id" + sessionDetailId +
                                " and SessionId " + sessionId));
    }

    @Override
    public SessionDetail getSessionDetailByState(String state) {
        return sessionDetailRepository.findByState(state)
                .orElseThrow(() -> new ResourceNotFoundException("SessionDetail", "State", state));
    }
}
