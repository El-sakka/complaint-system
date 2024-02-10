package com.example.complaint.controller;

import com.example.complaint.controller.user.UserController;
import com.example.complaint.controller.user.request.CreateUserRequest;
import com.example.complaint.controller.user.request.UserLoginRequest;
import com.example.complaint.model.dto.TokenResponse;
import com.example.complaint.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
public class UserControllerTest {

    @Mock
    private UserService userService;

    @InjectMocks
    private UserController userController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }


    @Test
    void createUser_ValidRequest_Success() {
        CreateUserRequest request = new CreateUserRequest();
        request.setUserName("testUser");
        request.setPassword("testPassword");
        TokenResponse tokenResponse = new TokenResponse();
        tokenResponse.setToken("eyJhbGciOiJIUzM4NCJ9.eyJzdWIiOiJqb2huX2RvZTI1ODUiLCJpYXQiOjE3MDc1NzQ4NDgsImV4cCI6MTcwNzU3NTc0OH0.U96phcDe_ITGXx0YUzOXzEeO6eUPpRLgfEMihqVsmX14NakmnNp4ldzaC5WJNyfh");

        ResponseEntity<TokenResponse> responseEntity = userController.createUser(request);

        assertEquals(HttpStatus.CREATED, responseEntity.getStatusCode());
    }

    @Test
    void loginUser_ValidRequest_Success() {
        UserLoginRequest request = new UserLoginRequest();
        request.setUserName("testUser");
        request.setPassword("testPassword");
        TokenResponse tokenResponse = new TokenResponse();
        tokenResponse.setToken("eyJhbGciOiJIUzM4NCJ9.eyJzdWIiOiJqb2huX2RvZTI1ODUiLCJpYXQiOjE3MDc1NzQ4NDgsImV4cCI6MTcwNzU3NTc0OH0.U96phcDe_ITGXx0YUzOXzEeO6eUPpRLgfEMihqVsmX14NakmnNp4ldzaC5WJNyfh");

        ResponseEntity<TokenResponse> responseEntity = userController.loginUser(request);

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
    }
}
