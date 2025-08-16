package com.said.lms.model;

import com.said.lms.enums.AccessLevel;
import com.said.lms.enums.DifficultyLevel;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Course {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long courseId;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String description;

    @Enumerated(EnumType.STRING)
    private DifficultyLevel difficulty;

    @Column(nullable = false)
    private String specialization;


    @Enumerated(EnumType.STRING)
    private AccessLevel accessLevel;

    @ManyToOne
    @JoinColumn(name = "teacher_id")
    private Teacher teacher;
}
