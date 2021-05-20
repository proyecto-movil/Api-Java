package com.example.ilenguageapi.service;

import com.example.ilenguageapi.domain.model.LanguageOfInterest;
import com.example.ilenguageapi.domain.repository.LanguageOfInterestRespository;
import com.example.ilenguageapi.domain.service.LanguageOfInterestService;
import com.example.ilenguageapi.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class LanguageOfInterestServiceImpl implements LanguageOfInterestService {
    @Autowired
    private LanguageOfInterestRespository languageOfInterestRespository;

    @Override
    public Page<LanguageOfInterest> getAllTopics(Pageable pageable) {
        return languageOfInterestRespository.findAll(pageable);
    }

    @Override
    public LanguageOfInterest getLanguaeById(Long languageId) {
        return languageOfInterestRespository.findById(languageId)
                .orElseThrow(() -> new ResourceNotFoundException("LanguageOfInterest", "Id", languageId));
    }

    @Override
    public LanguageOfInterest createLanguage(LanguageOfInterest language) {
        return languageOfInterestRespository.save(language);
    }

    @Override
    public LanguageOfInterest updateLanguage(Long languageId, LanguageOfInterest languageDetails) {
        LanguageOfInterest language = languageOfInterestRespository.findById(languageId)
                .orElseThrow(() -> new ResourceNotFoundException("LanguageOfInterest", "Id", languageId));
        return languageOfInterestRespository.save(
                language.setNameLanguage(languageDetails.getNameLanguage())
        );
    }

    @Override
    public ResponseEntity<?> deleteLanguage(Long languageId) {
        LanguageOfInterest language = languageOfInterestRespository.findById(languageId)
                .orElseThrow(() -> new ResourceNotFoundException("LanguageOfInterest", "Id", languageId));
        languageOfInterestRespository.delete(language);
        return ResponseEntity.ok().build();
    }
}
