package com.example.ilenguageapi.controller;

import com.example.ilenguageapi.domain.model.User;
import com.example.ilenguageapi.domain.service.UserService;
import com.example.ilenguageapi.resource.SaveUserResource;
import com.example.ilenguageapi.resource.UserResource;
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
    @GetMapping("/user")
    public Page<UserResource> getAllUsers(Pageable pageable){
        Page<User> userPage = userService.getAllUsers(pageable);
        List<UserResource> resources = userPage.getContent()
                .stream()
                .map(this::convertToResource)
                .collect(Collectors.toList());
        return new PageImpl<>(resources, pageable, resources.size());
    }
    @GetMapping("/user/{userId}")
    public UserResource getUserWithId(@PathVariable Long userId){
        return convertToResource(userService.getUserById(userId));
    }
    @PostMapping("/user")
    public UserResource createUser(@Valid @RequestBody SaveUserResource resource) {
        User user = convertToEntity(resource);
        return convertToResource(userService.createUser(user));
    }
    @PutMapping("/user/{userId}")
    public UserResource updateUser(@PathVariable Long userId, @Valid @RequestBody SaveUserResource resource) {
        User user = convertToEntity(resource);
        return convertToResource(userService.updateUser(userId, user));
    }
    @DeleteMapping("/user/{userId}")
    public ResponseEntity<?> deleteUser(@PathVariable Long userId){
        return userService.deleteUser(userId);
    }
}
