package com.sumana.smartdiary.repository;

import com.sumana.smartdiary.entity.DiaryEntry;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DiaryEntryRepository
        extends JpaRepository<DiaryEntry, Long> {
}