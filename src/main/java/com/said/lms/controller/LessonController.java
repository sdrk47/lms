package com.said.lms.controller;

import com.said.lms.dto.LessonDto;
import com.said.lms.model.Lesson;
import com.said.lms.service.LessonService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/lessons")
public class LessonController {

    private final LessonService lessonService;

    @PostMapping("/create")
    public Lesson create(@AuthenticationPrincipal UserDetails userDetails,
                         @RequestBody LessonDto lessonDto) {
        return lessonService.create(userDetails, lessonDto);
    }

    @GetMapping("/{lessonId}")
    public Lesson getLessonById(@PathVariable Long lessonId) {
        return lessonService.getLessonById(lessonId);
    }

    @GetMapping("/in-course/{courseId}")
    public List<Lesson> getLessonsByCourseId(@PathVariable Long courseId) {
        return lessonService.getLessonsOfCourse(courseId);
    }
}
