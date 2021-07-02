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

    @Operation(summary = "Get Users by Role Id", description = "Get All User by RoleId", tags = {"Users"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "All Users returned", content = @Content(mediaType = "application/json")),
            @ApiResponse(responseCode = "404", description = "Users not found")
    })
    @GetMapping("/role/{roleId}/users")
    public Page<UserResource> getAllUsersByRoleId(@PathVariable Long roleId,Pageable pageable){
        Page<User> userPage = userService.getAllUsersByRoleId(roleId, pageable);
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
    @PostMapping("/user")
    public UserResource createUser(@Valid @RequestBody SaveUserResource resource) {
        User user = convertToEntity(resource);
        //User UserWithRole = userService.createUser(userService.assignRoleById(user,roleId));
        User UserWithRole = userService.createUser(user);
        return convertToResource(UserWithRole);
    }

    @Operation(summary = "Assign Role to User", description = "Assign role to user with role id and user id", tags = {"Users"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Assigned Role to User", content = @Content(mediaType = "application/json")),
    })
    @PostMapping("/user/{userId}/role/{roleId}")
    public UserResource assignRoleToUser(@PathVariable Long userId, @PathVariable Long roleId){
       return convertToResource(userService.assignRoleByIdAndUserId(userId,roleId));
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

    @Operation(summary = "Update Media User", description = "Update Media user by userId", tags = {"Users"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "User Media Updated", content = @Content(mediaType = "application/json")),
    })
    @PutMapping("/user/{userId}/media")
    public UserResource updateRankingMedia(@PathVariable Long userId){
        return convertToResource(userService.updateMedia(userId));
    }
    @Operation(summary = "Delete User", description = "Deleted user by userId", tags = {"Users"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "User deleted", content = @Content(mediaType = "application/json")),
    })
    @DeleteMapping("/user/{userId}")
    public ResponseEntity<?> deleteUser(@PathVariable Long userId){
        return userService.deleteUser(userId);
    }

    @Operation(summary = "List tuthors", description = "List tuthors by language id and topic id", tags = {"Users"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Returned all tuthors", content = @Content(mediaType = "application/json")),
    })
    @GetMapping("/languages/{languageId}/topics/{topicId}/tuthors")
    public Page<UserResource> getAllTuthorsByLanguageIdAndTopicId(@PathVariable Long languageId, @PathVariable Long topicId, Pageable pageable){
        Page<User> userPage = userService.getAllTuthorsByTopicIdAndLanguageId(topicId,languageId,pageable);
        List<UserResource> resources = userPage.getContent()
                .stream()
                .map(this::convertToResource)
                .collect(Collectors.toList());
        return new PageImpl<>(resources, pageable, resources.size());
    }

    @Operation(summary = "List users", description = "List users by session id", tags = {"Users"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Returned all users", content = @Content(mediaType = "application/json")),
    })
    @GetMapping("/sessions/{sessionId}/users")
    public Page<UserResource> getAllUsersBySessionId(@PathVariable Long sessionId, Pageable pageable) {
        List<UserResource> users = userService.getAllUsersBySessionId(sessionId, pageable)
                .getContent().stream().map(this::convertToResource)
                .collect(Collectors.toList());
        int userCount = users.size();
        return new PageImpl<>(users, pageable, userCount);
    }
}
