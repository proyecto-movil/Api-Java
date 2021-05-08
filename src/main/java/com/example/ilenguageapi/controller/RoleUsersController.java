package com.example.ilenguageapi.controller;

import com.example.ilenguageapi.domain.model.Role;
import com.example.ilenguageapi.domain.service.RoleService;
import com.example.ilenguageapi.resource.RoleResource;
import com.example.ilenguageapi.resource.SaveRoleResource;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.persistence.PostRemove;

@RestController
@RequestMapping("/api")
public class RoleUsersController {
    @Autowired
    private ModelMapper mapper;

    @Autowired
    private RoleService roleService;

    @PostMapping("/roles/{roleId}/users/{userId}")
    public RoleResource assignRoleUser(@PathVariable Long roleId,@PathVariable Long userId){
     return convertToResource(roleService.assignRoleUser(roleId,userId));
    }

    private Role convertToEntity(SaveRoleResource resource){
        return mapper.map(resource,Role.class);
    }
    private RoleResource convertToResource(Role entity){
        return mapper.map(entity,RoleResource.class);
    }
}
