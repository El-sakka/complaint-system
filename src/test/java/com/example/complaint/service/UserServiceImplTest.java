package com.example.complaint.service;

import com.example.complaint.Util.JwtUtil;
import com.example.complaint.Util.UserTestUtil;
import com.example.complaint.controller.user.request.CreateUserRequest;
import com.example.complaint.controller.user.request.UserLoginRequest;
import com.example.complaint.exception.InValidPasswordException;
import com.example.complaint.exception.UserExistException;
import com.example.complaint.exception.UserNotFoundException;
import com.example.complaint.model.dto.TokenResponse;
import com.example.complaint.model.entity.User;
import com.example.complaint.repository.UserRepository;
import com.example.complaint.service.impl.UserServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UserServiceImplTest {

    @Mock
    UserRepository userRepository;

    @Mock
    JwtUtil jwtService;

    @Mock
    PasswordEncoder passwordEncoder;

    @InjectMocks
    UserServiceImpl userService;

    @Test
    void createUser_ValidRequest_Success() {
        CreateUserRequest request = new CreateUserRequest();
        request.setUserName("testUser");
        request.setPassword("testPassword");

        when(userRepository.existsByUserName(request.getUserName())).thenReturn(false);
        when(passwordEncoder.encode(request.getPassword())).thenReturn("encodedPassword");
        when(userRepository.save(any(User.class))).thenReturn(new User());
        when(jwtService.generateJwtToken(any(User.class))).thenReturn("testToken");

        TokenResponse response = userService.createUser(request);

        assertNotNull(response);
        assertEquals("testToken", response.getToken());

    }

    @Test
    void createUser_UserExists_ExceptionThrown() {
        CreateUserRequest request = new CreateUserRequest();
        request.setUserName("existingUser");

        when(userRepository.existsByUserName(request.getUserName())).thenReturn(true);

        assertThrows(UserExistException.class, () -> userService.createUser(request));


    }

    @Test
    void loginUser_InvalidPassword_ExceptionThrown() {

        UserLoginRequest userLoginRequest = UserTestUtil.createValidUserLoginRequest();
        User user = UserTestUtil.createValidUser();
        when(userRepository.findByUserName(userLoginRequest.getUserName())).thenReturn(Optional.of(user));
        when(passwordEncoder.matches(userLoginRequest.getPassword(), user.getPassword())).thenReturn(false);

        assertThrows(InValidPasswordException.class, () -> userService.loginUser(userLoginRequest));

    }


    @Test
    void loginUser_UserNotFound_ExceptionThrown() {

        UserLoginRequest userLoginRequest = UserTestUtil.createValidUserLoginRequest();
        when(userRepository.findByUserName(userLoginRequest.getUserName())).thenReturn(Optional.empty());

        assertThrows(UserNotFoundException.class, () -> userService.loginUser(userLoginRequest));

        verify(userRepository).findByUserName(userLoginRequest.getUserName());
    }
}