package com.example.ilenguageapi.service;

import com.example.ilenguageapi.domain.model.Role;
import com.example.ilenguageapi.domain.model.User;
import com.example.ilenguageapi.domain.repository.RoleRepository;
import com.example.ilenguageapi.domain.service.RoleService;
import com.example.ilenguageapi.exception.ResourceNotFoundException;
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
        return roleRepository.findById(roleId)
                .orElseThrow(() -> new ResourceNotFoundException("Role", "Id", roleId));
    }
    @Override
    public Role updateRole(Long roleId, Role roleRequest) {
        Role role = roleRepository.findById(roleId)
                .orElseThrow(() -> new ResourceNotFoundException("Role", "Id", roleId));
        role.setName(roleRequest.getName());
        return roleRepository.save(role);
    }
    @Override
    public ResponseEntity<?> deleteRole(Long roleId) {
        Role role = roleRepository.findById(roleId)
                .orElseThrow(() -> new ResourceNotFoundException("Role", "Id", roleId));
        roleRepository.delete(role);
        return ResponseEntity.ok().build();
    }

    @Override
    public Role getRoleByName(String name) {
        return roleRepository.findByName(name)
                .orElseThrow(()->new ResourceNotFoundException("Role","name",name));
    }

    @Override
    public Role createRole(Role role) {
        return roleRepository.save(role);
    }
}
