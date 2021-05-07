package com.example.ilenguageapi.service;

import com.example.ilenguageapi.domain.model.Role;
import com.example.ilenguageapi.domain.model.User;
import com.example.ilenguageapi.domain.repository.RoleRepository;
import com.example.ilenguageapi.domain.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class RoleServiceImpl implements RoleService {

    @Autowired
    private RoleRepository roleRepository;

    @Override
    public Page<Role> getAllRoles(Pageable pageable) {

        return roleRepository.findAll(pageable);
    }

    @Override
    public Role getRoleById(Long roleId) {
        return null;
    }

    @Override
    public Role updateRole(Long roleId, Role role) {
        return null;
    }

    @Override
    public ResponseEntity<?> deleteRole(Long roleId) {
        return null;
    }

    @Override
    public Role getRoleByUserId(User userId) {
        return null;
    }

    @Override
    public Role assignRoleUser(Long roleId, Long userId) {
        return null;
    }
}
