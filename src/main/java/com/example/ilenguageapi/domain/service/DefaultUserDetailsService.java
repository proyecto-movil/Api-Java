package com.example.ilenguageapi.domain.service;

import com.example.ilenguageapi.domain.model.User;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

public interface DefaultUserDetailsService extends UserDetailsService {
    List<User> getAll();
}
