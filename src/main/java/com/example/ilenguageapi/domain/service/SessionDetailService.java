package com.example.ilenguageapi.domain.service;

import com.example.ilenguageapi.domain.model.Session;
import com.example.ilenguageapi.domain.model.SessionDetail;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

public interface SessionDetailService {
    Page<SessionDetail> getAllPosts(Pageable pageable);
    SessionDetail getSessionDetailById(Long sessionDetailId);
    SessionDetail createSessionDetail(SessionDetail sessionDetail);
    SessionDetail updateSessionDetail(Long sessionDetailId, SessionDetail sessionDetailRequest);
    ResponseEntity<?> deleteSessionDetail(Long sessionDetailId);
    Page<SessionDetail> getAllSessionDetailsByUserId(Long userId, Pageable pageable);
    SessionDetail getSessionDetailByState(String state);

}
