package com.example.complaint.controller.user.request;

import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
public class UserLoginRequest {


    @NotNull(message = "Username cannot be null")
    @Size(min = 4, max = 20, message = "Username must be between 4 and 20 characters")
    private String userName;

    @NotNull(message = "Password cannot be null")
    @Size(min = 8, message = "Password must be at least 8 characters long")
    private String password;
}
