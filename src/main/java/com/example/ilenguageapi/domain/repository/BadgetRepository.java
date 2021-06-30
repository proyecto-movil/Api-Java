package com.example.ilenguageapi.domain.repository;

import com.example.ilenguageapi.domain.model.Badget;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BadgetRepository extends JpaRepository<Badget, Long> {
}
