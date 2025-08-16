package com.said.lms.controller;

import com.said.lms.dto.CourseDto;
import com.said.lms.model.Course;
import com.said.lms.service.CourseService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/courses")
public class CourseController {
    private final CourseService courseService;

    @PostMapping("/create-course")
    public Course createCourse(@AuthenticationPrincipal UserDetails userDetails,
                               @RequestBody CourseDto courseDto){
        return courseService.create(courseDto, userDetails);
    }
    @GetMapping("/all-courses")
    public List<Course> getAllCourses(){
        return courseService.findAll();
    }

    @GetMapping("/course/{courseId}")
    public Course getCourseById(@PathVariable Long courseId){
        return courseService.findById(courseId);
    }
}
