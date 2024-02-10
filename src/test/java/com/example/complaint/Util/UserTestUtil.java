package com.example.complaint.Util;

import com.example.complaint.controller.user.request.CreateUserRequest;
import com.example.complaint.controller.user.request.UserLoginRequest;
import com.example.complaint.model.entity.User;

public class UserTestUtil {

    public static CreateUserRequest createValidCreateUserRequest() {
        CreateUserRequest request = new CreateUserRequest();
        request.setUserName("testUser");
        request.setPassword("testPassword");
        return request;
    }

    public static UserLoginRequest createValidUserLoginRequest() {
        UserLoginRequest request = new UserLoginRequest();
        request.setUserName("testUser");
        request.setPassword("testPassword");
        return request;
    }

    public static User createValidUser() {
        User user = new User();
        user.setId("1");
        user.setUserName("testUser");
        user.setPassword("encodedPassword");
        return user;
    }
}
