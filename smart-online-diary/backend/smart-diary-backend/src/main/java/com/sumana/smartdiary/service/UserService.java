package com.sumana.smartdiary.service;

import com.sumana.smartdiary.dto.LoginRequest;
import com.sumana.smartdiary.dto.LoginResponse;
import com.sumana.smartdiary.dto.RegisterRequest;
import com.sumana.smartdiary.dto.RegisterResponse;
import com.sumana.smartdiary.entity.User;
import com.sumana.smartdiary.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private JwtService jwtService;

    public RegisterResponse register(RegisterRequest request) {

        User user = new User();
        user.setFullName(request.getFullName());
        user.setEmail(request.getEmail());
        user.setPassword(request.getPassword());

        User savedUser = userRepository.save(user);

        return new RegisterResponse(
                "User registered successfully",
                savedUser.getId(),
                savedUser.getEmail()
        );
    }

    public LoginResponse login(LoginRequest request) {

        Optional<User> userOptional = userRepository.findByEmail(request.getEmail());

        if (userOptional.isEmpty()) {
            return new LoginResponse("Invalid email or password", null);
        }

        User user = userOptional.get();

        if (!user.getPassword().equals(request.getPassword())) {
            return new LoginResponse("Invalid email or password", null);
        }

        String token = jwtService.generateToken(user.getEmail());

        return new LoginResponse("Login successful", token);
    }
}