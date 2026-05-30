package com.sumana.smartdiary.service;

import com.sumana.smartdiary.dto.DiaryRequest;
import com.sumana.smartdiary.dto.DiaryResponse;
import com.sumana.smartdiary.entity.DiaryEntry;
import com.sumana.smartdiary.repository.DiaryEntryRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class DiaryService {

    private final DiaryEntryRepository diaryEntryRepository;

    public DiaryService(DiaryEntryRepository diaryEntryRepository) {
        this.diaryEntryRepository = diaryEntryRepository;
    }

    public DiaryResponse createDiaryEntry(DiaryRequest request) {

        DiaryEntry diaryEntry = new DiaryEntry();
        diaryEntry.setTitle(request.getTitle());
        diaryEntry.setContent(request.getContent());
        diaryEntry.setCreatedAt(LocalDateTime.now());
        diaryEntry.setUpdatedAt(LocalDateTime.now());

        DiaryEntry savedEntry = diaryEntryRepository.save(diaryEntry);

        DiaryResponse response = new DiaryResponse();
        response.setId(savedEntry.getId());
        response.setTitle(savedEntry.getTitle());
        response.setContent(savedEntry.getContent());

        return response;
    }
}