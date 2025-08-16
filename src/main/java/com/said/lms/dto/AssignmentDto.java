package com.said.lms.dto;

import com.said.lms.enums.AssignmentType;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class AssignmentDto {

    private String title;
    private String description;
    private Long courseId;
    private Long lessonId;
    private LocalDateTime deadline;
    private AssignmentType assignmentType;
}
