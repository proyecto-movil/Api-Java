package com.example.ilenguageapi.domain.repository;

import com.example.ilenguageapi.domain.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role,Long> {
    public Optional<Role> findByName(String name);
}
