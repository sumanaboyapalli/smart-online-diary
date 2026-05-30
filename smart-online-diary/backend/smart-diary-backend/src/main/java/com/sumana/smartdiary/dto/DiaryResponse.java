package com.sumana.smartdiary.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DiaryResponse {

    private Long id;

    private String title;

    private String content;
}