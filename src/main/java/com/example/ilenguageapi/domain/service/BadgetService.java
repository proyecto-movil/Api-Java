package com.example.ilenguageapi.domain.service;


import com.example.ilenguageapi.domain.model.Badget;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

public interface BadgetService {
    Page<Badget> getAllBadgets(Pageable pageable);
    Page<Badget> getAllBadgetsByUserId(Long userId, Pageable pageable);
    Badget getBabgetById(Long badgetId);
    Badget createBadget(Badget badget);
    Badget updateBadget(Long badgetId, Badget badgetDetails);
    ResponseEntity<?> deleteBadget(Long badgetId);
}
