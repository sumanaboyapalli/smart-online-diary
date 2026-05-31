package com.sumana.smartdiary.controller;

import com.sumana.smartdiary.dto.DiaryRequest;
import com.sumana.smartdiary.dto.DiaryResponse;
import com.sumana.smartdiary.service.DiaryService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/diary")
public class DiaryController {

    private final DiaryService diaryService;

    public DiaryController(DiaryService diaryService) {
        this.diaryService = diaryService;
    }

    @GetMapping("/test")
    public java.util.Map<String, String> test() {
        return java.util.Map.of("message", "Protected diary endpoint");
    }

    @PostMapping
    public DiaryResponse createDiaryEntry(@RequestBody DiaryRequest request) {
        return diaryService.createDiaryEntry(request);
    }

    @GetMapping
    public List<DiaryResponse> getAllDiaryEntries() {
        return diaryService.getAllDiaryEntries();
    }
}