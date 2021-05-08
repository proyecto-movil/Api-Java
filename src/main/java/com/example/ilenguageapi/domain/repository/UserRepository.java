package com.example.ilenguageapi.domain.repository;

import com.example.ilenguageapi.domain.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
