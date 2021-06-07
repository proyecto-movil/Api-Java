package com.example.ilenguageapi.domain.repository;

import com.example.ilenguageapi.domain.model.LanguageOfInterest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LanguageOfInterestRespository extends JpaRepository<LanguageOfInterest, Long> {
}
