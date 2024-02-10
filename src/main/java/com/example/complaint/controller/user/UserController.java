package com.example.complaint.controller.user;


import com.example.complaint.controller.user.request.CreateUserRequest;
import com.example.complaint.controller.user.request.UserLoginRequest;
import com.example.complaint.model.dto.TokenResponse;
import com.example.complaint.service.UserService;
import io.swagger.annotations.*;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@RequestMapping("/users")
@Api(tags = "User Management")
public class UserController {

    private final UserService userService;

    @PostMapping
    @ApiOperation(value = "Create a new user", notes = "Endpoint to register a new user")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "User created successfully", response = TokenResponse.class),
            @ApiResponse(code = 400, message = "Bad request")
    })
    public ResponseEntity<TokenResponse> createUser(
            @ApiParam(value = "User registration request", required = true)
            @Valid @RequestBody CreateUserRequest request) {
        return new ResponseEntity<>(userService.createUser(request), HttpStatus.CREATED);
    }

    @PostMapping("/login")
    @ApiOperation(value = "Login user", notes = "Endpoint to authenticate and log in a user")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "User logged in successfully", response = TokenResponse.class),
            @ApiResponse(code = 400, message = "Bad request"),
            @ApiResponse(code = 401, message = "Unauthorized")
    })
    public ResponseEntity<TokenResponse> loginUser(
            @ApiParam(value = "User login request", required = true)
            @Valid @RequestBody UserLoginRequest request) {
        return new ResponseEntity<>(userService.loginUser(request), HttpStatus.OK);
    }

}
