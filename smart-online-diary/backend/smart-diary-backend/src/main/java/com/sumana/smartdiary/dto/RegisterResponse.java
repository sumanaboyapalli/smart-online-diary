package com.sumana.smartdiary.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class RegisterResponse {

    private String message;
    private Long userId;
    private String email;
}