package com.example.ilenguageapi.domain.repository;

import com.example.ilenguageapi.domain.model.Role;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface RoleRepository extends JpaRepository<Role,Long> {
     Optional<Role> findByName(String name);
     Page<Role>findById(Long id, Pageable pageable);
     Optional<Role> findByUserId(Long id);
}
