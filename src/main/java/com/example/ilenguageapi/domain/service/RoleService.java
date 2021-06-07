package com.example.ilenguageapi.domain.service;

import com.example.ilenguageapi.domain.model.Role;
import com.example.ilenguageapi.domain.model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

public interface RoleService {
    Page<Role>getAllRoles(Pageable pageable);
    Role getRoleById(Long roleId);
    Role updateRole(Long roleId,Role role);
    ResponseEntity<?> deleteRole(Long roleId);
    Role getRoleByName(String name);
    Role createRole(Role role);

}