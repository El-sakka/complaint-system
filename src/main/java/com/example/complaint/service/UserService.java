package com.example.complaint.service;

import com.example.complaint.controller.user.request.CreateUserRequest;
import com.example.complaint.controller.user.request.UserLoginRequest;
import com.example.complaint.model.dto.TokenResponse;
import com.example.complaint.model.entity.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import java.util.Optional;

public interface UserService {

    TokenResponse createUser(CreateUserRequest request);
    TokenResponse loginUser(UserLoginRequest request);

    User findByUserName(String userName);

    void validateUserExistence(String userName);
}
