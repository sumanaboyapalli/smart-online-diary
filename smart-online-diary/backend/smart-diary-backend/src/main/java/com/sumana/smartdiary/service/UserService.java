package com.sumana.smartdiary.service;

import com.sumana.smartdiary.dto.RegisterRequest;
import com.sumana.smartdiary.dto.RegisterResponse;
import com.sumana.smartdiary.entity.User;
import com.sumana.smartdiary.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

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
}