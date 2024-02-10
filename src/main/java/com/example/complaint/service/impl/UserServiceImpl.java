package com.example.complaint.service.impl;

import com.example.complaint.Util.JwtUtil;
import com.example.complaint.controller.user.request.CreateUserRequest;
import com.example.complaint.controller.user.request.UserLoginRequest;
import com.example.complaint.exception.InValidPasswordException;
import com.example.complaint.exception.UserExistException;
import com.example.complaint.exception.UserNotFoundException;
import com.example.complaint.model.dto.TokenResponse;
import com.example.complaint.model.entity.User;
import com.example.complaint.repository.UserRepository;
import com.example.complaint.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {


    private final UserRepository userRepository;
    private final JwtUtil jwtService;
    private final PasswordEncoder passwordEncoder;

    @Override
    public TokenResponse createUser(CreateUserRequest request) {
        validateUserExistence(request.getUserName());

        User user = User.builder()
                .userName(request.getUserName())
                .password(passwordEncoder.encode(request.getPassword()))
                .build();
        user = userRepository.save(user);
        String token = jwtService.generateJwtToken(user);
        return TokenResponse.builder()
                .token(token).build();
    }

    @Override
    public TokenResponse loginUser(UserLoginRequest request) {

        User user = userRepository.findByUserName(request.getUserName()).orElseThrow(UserNotFoundException::new);
        if (!passwordEncoder.matches(request.getPassword(), user.getPassword())) {
            throw new InValidPasswordException();
        }
        return TokenResponse.builder()
                .token(jwtService.generateJwtToken(user)).build();
    }

    @Override
    public User findByUserName(String userName) {
        return userRepository.findByUserName(userName).orElseThrow(UserNotFoundException::new);
    }

    @Override
    public void validateUserExistence(String userName){
        boolean isExist = userRepository.existsByUserName(userName);
        if(isExist){
            throw new UserExistException();
        }
    }
}
