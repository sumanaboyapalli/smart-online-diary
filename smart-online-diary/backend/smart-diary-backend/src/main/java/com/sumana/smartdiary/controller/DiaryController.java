package com.sumana.smartdiary.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class DiaryController {

    @GetMapping("/api/diary/test")
    public Map<String, String> test() {

        return Map.of(
                "message",
                "Protected diary endpoint"
        );
    }
}