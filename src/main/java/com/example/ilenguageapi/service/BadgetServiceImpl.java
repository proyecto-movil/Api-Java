package com.example.ilenguageapi.service;

import com.example.ilenguageapi.domain.model.Badget;
import com.example.ilenguageapi.domain.repository.BadgetRepository;
import com.example.ilenguageapi.domain.repository.UserRepository;
import com.example.ilenguageapi.domain.service.BadgetService;
import com.example.ilenguageapi.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BadgetServiceImpl implements BadgetService {
    @Autowired
    private BadgetRepository badgetRepository;
    @Autowired
    private UserRepository userRepository;

    @Override
    public Page<Badget> getAllBadgets(Pageable pageable) {
        return badgetRepository.findAll(pageable);
    }

    @Override
    public Page<Badget> getAllBadgetsByUserId(Long userId, Pageable pageable) {
        return userRepository.findById(userId).map( user -> {
            List<Badget> badgets = user.getBadgets();
            int badgetCount = badgets.size();
            return new PageImpl<>(badgets, pageable, badgetCount);
        }).orElseThrow(() -> new ResourceNotFoundException("User", "Id", userId));
    }

    @Override
    public Badget getBabgetById(Long badgetId) {
        return badgetRepository.findById(badgetId)
                .orElseThrow(() -> new ResourceNotFoundException("Badget","Id",badgetId));
    }

    @Override
    public Badget createBadget(Badget badget) {
        return badgetRepository.save(badget);
    }

    @Override
    public Badget updateBadget(Long badgetId, Badget badgetDetails) {
        Badget badget = badgetRepository.findById(badgetId)
                .orElseThrow(() -> new ResourceNotFoundException("Badget","Id",badgetId));
        return badgetRepository.save(
               badget.setTitle(badgetDetails.getTitle())
                       .setDescription(badgetDetails.getDescription())
                       .setImgSrc(badgetDetails.getImgSrc())
        );
    }
    @Override
    public ResponseEntity<?> deleteBadget(Long badgetId) {
        Badget badget = badgetRepository.findById(badgetId)
                .orElseThrow(() -> new ResourceNotFoundException("Badget","Id",badgetId));
        badgetRepository.delete(badget);
        return ResponseEntity.ok().build();
    }
}
