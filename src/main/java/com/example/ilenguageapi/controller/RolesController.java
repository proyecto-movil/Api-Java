package com.example.ilenguageapi.controller;

import com.example.ilenguageapi.domain.model.Role;
import com.example.ilenguageapi.domain.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class RolesController {

    @Autowired
    private RoleService roleService;

    @GetMapping("/roles")
    public Page<Role>getAllRoles(Pageable pageable){
        Page<Role> rolesPage = roleService.getAllRoles(pageable);
        return rolesPage;
    }


}
