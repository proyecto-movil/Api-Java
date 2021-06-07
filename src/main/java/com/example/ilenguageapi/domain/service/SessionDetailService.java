package com.example.ilenguageapi.domain.service;

import com.example.ilenguageapi.domain.model.SessionDetail;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

public interface SessionDetailService {
    Page<SessionDetail> getAllSessionDetailsBySessionId(Long sessionId, Pageable pageable);
    SessionDetail getSessionDetailByIdAndSessionId(Long sessionId, Long sessionDetailId);
    SessionDetail createSessionDetail(Long sessionId, SessionDetail sessionDetail);
    SessionDetail updateSessionDetail(Long sessionId, Long sessionDetailId, SessionDetail sessionDetailDetails);
    ResponseEntity<?> deleteSessionDetail(Long sessionId, Long sessionDetailId);
    SessionDetail getSessionDetailByState(String state);
}
