package com.sumana.smartdiary.service;

import com.sumana.smartdiary.dto.DiaryRequest;
import com.sumana.smartdiary.dto.DiaryResponse;
import com.sumana.smartdiary.entity.DiaryEntry;
import com.sumana.smartdiary.repository.DiaryEntryRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

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

    public List<DiaryResponse> getAllDiaryEntries() {

        return diaryEntryRepository.findAll()
                .stream()
                .map(entry -> {
                    DiaryResponse response = new DiaryResponse();
                    response.setId(entry.getId());
                    response.setTitle(entry.getTitle());
                    response.setContent(entry.getContent());
                    return response;
                })
                .toList();
    }
    public DiaryResponse getDiaryEntryById(Long id) {

        DiaryEntry entry = diaryEntryRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Diary entry not found"));

        DiaryResponse response = new DiaryResponse();
        response.setId(entry.getId());
        response.setTitle(entry.getTitle());
        response.setContent(entry.getContent());

        return response;
    }
    public DiaryResponse updateDiaryEntry(Long id, DiaryRequest request) {

        DiaryEntry entry = diaryEntryRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Diary entry not found"));

        entry.setTitle(request.getTitle());
        entry.setContent(request.getContent());
        entry.setUpdatedAt(java.time.LocalDateTime.now());

        DiaryEntry updatedEntry = diaryEntryRepository.save(entry);

        DiaryResponse response = new DiaryResponse();
        response.setId(updatedEntry.getId());
        response.setTitle(updatedEntry.getTitle());
        response.setContent(updatedEntry.getContent());

        return response;
    }
}