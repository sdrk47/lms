package com.said.lms.service;

import com.said.lms.dto.CourseDto;
import com.said.lms.exception.CourseNotFoundException;
import com.said.lms.model.Course;
import com.said.lms.model.Teacher;
import com.said.lms.model.User;
import com.said.lms.repository.CourseRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CourseService {

    private final CourseRepository courseRepository;
    private final TeacherService teacherService;
    private final UserService userService;

    public Course create(CourseDto courseDto, UserDetails userDetails) {
        User user = userService.findByEmail(userDetails.getUsername());

        Teacher teacher = teacherService.getByUserId(user.getUserId());

        Course course = Course.builder()
                .accessLevel(courseDto.getAccessLevel())
                .title(courseDto.getTitle())
                .teacher(teacher)
                .description(courseDto.getDescription())
                .difficulty(courseDto.getDifficulty())
                .specialization(courseDto.getSpecialization())
                .build();
        courseRepository.save(course);
        return course;
    }

    public List<Course> findAll() {
        return courseRepository.findAll();
    }

    public Course findById(Long id) {
        return courseRepository.findById(id).orElseThrow(()->
                new CourseNotFoundException("Course not found"));
    }
}
