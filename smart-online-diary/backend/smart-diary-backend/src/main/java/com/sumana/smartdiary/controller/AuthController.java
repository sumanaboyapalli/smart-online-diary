package com.sumana.smartdiary.controller;

import com.sumana.smartdiary.dto.LoginRequest;
import com.sumana.smartdiary.dto.LoginResponse;
import com.sumana.smartdiary.dto.RegisterRequest;
import com.sumana.smartdiary.dto.RegisterResponse;
import com.sumana.smartdiary.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private UserService userService;

    @PostMapping("/register")
    public RegisterResponse register(@Valid @RequestBody RegisterRequest request) {
        return userService.register(request);
    }

    @PostMapping("/login")
    public LoginResponse login(@RequestBody LoginRequest request) {
        return userService.login(request);
    }
}