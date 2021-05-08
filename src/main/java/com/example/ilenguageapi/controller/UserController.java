package com.example.ilenguageapi.controller;

import com.example.ilenguageapi.domain.model.User;
import com.example.ilenguageapi.domain.service.UserService;
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
public class UserController {
    @Autowired
    private UserService userService;

    @Autowired
    private ModelMapper modelMapper;

    private User convertToEntity(SaveUserResource resource) {
        return modelMapper.map(resource, User.class);
    }

    private UserResource convertToResource(User entity) {
        return modelMapper.map(entity, UserResource.class);
    }
    @Operation(summary = "Get Users", description = "Get All User by Pages", tags = {"Users"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "All Users returned", content = @Content(mediaType = "application/json")),
            @ApiResponse(responseCode = "404", description = "Users not found")
    })
    @GetMapping("/user")
    public Page<UserResource> getAllUsers(Pageable pageable){
        Page<User> userPage = userService.getAllUsers(pageable);
        List<UserResource> resources = userPage.getContent()
                .stream()
                .map(this::convertToResource)
                .collect(Collectors.toList());
        return new PageImpl<>(resources, pageable, resources.size());
    }
    @Operation(summary = "Get User", description = "Get User by userId", tags = {"Users"})
    @ApiResponses(value = {
           @ApiResponse(responseCode = "200", description = "User returned", content = @Content(mediaType = "application/json")),
           @ApiResponse(responseCode = "404", description = "User not found")
    })
    @GetMapping("/user/{userId}")
    public UserResource getUserWithId(@PathVariable Long userId){
        return convertToResource(userService.getUserById(userId));
    }

    @Operation(summary = "Add User", description = "Create new user", tags = {"Users"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "User created", content = @Content(mediaType = "application/json")),
    })
    @PostMapping("/user/{roleId}")
    public UserResource createUser(@Valid @RequestBody SaveUserResource resource, @PathVariable Long roleId) {
        User user = convertToEntity(resource);
        return convertToResource(userService.createUser(userService.assignRoleById(user,roleId)));
    }

    @Operation(summary = "Update User", description = "Update user by userId", tags = {"Users"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "User Updated", content = @Content(mediaType = "application/json")),
    })
    @PutMapping("/user/{userId}")
    public UserResource updateUser(@PathVariable Long userId, @Valid @RequestBody SaveUserResource resource) {
        User user = convertToEntity(resource);
        return convertToResource(userService.updateUser(userId, user));
    }
    @Operation(summary = "Delete User", description = "Delte user by userId", tags = {"Users"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "User deleted", content = @Content(mediaType = "application/json")),
    })
    @DeleteMapping("/user/{userId}")
    public ResponseEntity<?> deleteUser(@PathVariable Long userId){
        return userService.deleteUser(userId);
    }
}
