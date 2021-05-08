package com.example.ilenguageapi.controller;

import com.example.ilenguageapi.domain.model.Role;
import com.example.ilenguageapi.domain.repository.RoleRepository;
import com.example.ilenguageapi.domain.service.RoleService;
import com.example.ilenguageapi.resource.RoleResource;
import com.example.ilenguageapi.resource.SaveRoleResource;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api")
public class RolesController {

    @Autowired
    private RoleService roleService;
    @Autowired
    private ModelMapper mapper;

    @GetMapping("/roles")
    public Page<RoleResource> getAllRoles(Pageable pageable) {
        Page<Role> rolesPage = roleService.getAllRoles(pageable);
        List<RoleResource> resources = rolesPage.getContent()
                .stream()
                .map(this::convertToResource)
                .collect(Collectors.toList());
        return new PageImpl<>(resources, pageable, resources.size());
    }

    @PutMapping("/roles/{roleId}")
    public RoleResource updateRole(@PathVariable Long roleId, @Valid @RequestBody SaveRoleResource resource) {
        Role role = convertToEntity(resource);
        return convertToResource(roleService.updateRole(roleId, role));
    }

    @DeleteMapping("/roles/{roleId}")
    public ResponseEntity<?> deleteRole(@PathVariable Long roleId) {
        return roleService.deleteRole(roleId);
    }

    @GetMapping("/users/{userId}/roles")
    public RoleResource getRoleByUserId(@PathVariable Long userId) {
        return convertToResource(roleService.getRoleByUserId(userId));
    }

    @GetMapping("/roles/{roleId}")
    public RoleResource getRoleById(@PathVariable Long roleId){
        return convertToResource(roleService.getRoleById(roleId));
    }


    private Role convertToEntity(SaveRoleResource resource){return mapper.map(resource,Role.class);}
    private RoleResource convertToResource(Role entity){return mapper.map(entity,RoleResource.class);}


}
