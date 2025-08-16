package com.said.lms.dto;

import com.said.lms.enums.AccessLevel;
import com.said.lms.enums.DifficultyLevel;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CourseDto {
    private String title;
    private String description;
    private DifficultyLevel difficulty;
    private String specialization;
    private AccessLevel accessLevel;
}
