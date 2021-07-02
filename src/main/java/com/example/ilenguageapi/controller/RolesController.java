package com.example.ilenguageapi.controller;

import com.example.ilenguageapi.domain.model.Role;
import com.example.ilenguageapi.domain.model.User;
import com.example.ilenguageapi.domain.repository.RoleRepository;
import com.example.ilenguageapi.domain.service.RoleService;
import com.example.ilenguageapi.resource.RoleResource;
import com.example.ilenguageapi.resource.SaveRoleResource;
import com.example.ilenguageapi.resource.SaveUserResource;
import com.example.ilenguageapi.resource.UserResource;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
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

    @Operation(summary = "Get Roles", description = "Get All Role by Pages", tags = {"Roles"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "All Roles returned", content = @Content(mediaType = "application/json")),
            @ApiResponse(responseCode = "404", description = "Role not found")
    })
    @GetMapping("/roles")
    public Page<RoleResource> getAllRoles(Pageable pageable) {
        Page<Role> rolesPage = roleService.getAllRoles(pageable);
        List<RoleResource> resources = rolesPage.getContent()
                .stream()
                .map(this::convertToResource)
                .collect(Collectors.toList());
        return new PageImpl<>(resources, pageable, resources.size());
    }

    @Operation(summary = "Update Role", description = "Update role by roleId", tags = {"Roles"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Role Updated", content = @Content(mediaType = "application/json")),
    })
    @PutMapping("/roles/{roleId}")
    public RoleResource updateRole(@PathVariable Long roleId, @Valid @RequestBody SaveRoleResource resource) {
        Role role = convertToEntity(resource);
        return convertToResource(roleService.updateRole(roleId, role));
    }

    @Operation(summary = "Delete Role", description = "Deleted role by roleId", tags = {"Roles"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Role deleted", content = @Content(mediaType = "application/json")),
    })
    @DeleteMapping("/roles/{roleId}")
    public ResponseEntity<?> deleteRole(@PathVariable Long roleId) {
        return roleService.deleteRole(roleId);
    }

    @Operation(summary = "Get Role", description = "Get Role by roleId", tags = {"Roles"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Role returned", content = @Content(mediaType = "application/json")),
            @ApiResponse(responseCode = "404", description = "Role not found")
    })
    @GetMapping("/roles/{roleId}")
    public RoleResource getRoleById(@PathVariable Long roleId){
        return convertToResource(roleService.getRoleById(roleId));
    }

    @Operation(summary = "Get Role", description = "Get User by name", tags = {"Roles"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Role returned", content = @Content(mediaType = "application/json")),
            @ApiResponse(responseCode = "404", description = "Role not found")
    })
    @GetMapping("/roles/name/{name}")
    public RoleResource getRoleByName(@PathVariable String name){
        return convertToResource(roleService.getRoleByName(name));
    }

    @Operation(summary = "Add Role", description = "Create new role", tags = {"Roles"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Role created", content = @Content(mediaType = "application/json")),
    })
    @PostMapping("/role")
    public RoleResource createRole(@Valid @RequestBody SaveRoleResource resource){
        return convertToResource(roleService.createRole(convertToEntity(resource)));
    }

    private Role convertToEntity(SaveRoleResource resource){return mapper.map(resource,Role.class);}
    private RoleResource convertToResource(Role entity){return mapper.map(entity,RoleResource.class);}
}
