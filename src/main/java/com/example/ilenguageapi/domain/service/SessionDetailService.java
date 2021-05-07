package com.example.ilenguageapi.domain.service;

import com.example.ilenguageapi.domain.model.Session;
import com.example.ilenguageapi.domain.model.SessionDetail;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

public interface SessionDetailService {
    Page<SessionDetail> getAllSessionDetails(Pageable pageable);
    SessionDetail getSessionDetailById(Long sessionDetailId);
    SessionDetail createSessionDetail(SessionDetail sessionDetail);
    SessionDetail updateSessionDetail(Long sessionDetailId, SessionDetail sessionDetailRequest);
    ResponseEntity<?> deleteSessionDetail(Long sessionDetailId);
    SessionDetail getSessionDetailByIdAndSessionId(Long sessionId, Long sessionDetailId);
    SessionDetail getSessionDetailByState(String state);

}
