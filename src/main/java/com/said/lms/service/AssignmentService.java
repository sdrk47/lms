package com.said.lms.service;

import com.said.lms.dto.AssignmentDto;
import com.said.lms.model.Assignment;
import com.said.lms.model.Course;
import com.said.lms.model.Lesson;
import com.said.lms.repository.AssignmentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AssignmentService {

    private final AssignmentRepository assignmentRepository;
    private final CourseService courseService;
    private final LessonService lessonService;

    public Assignment save(AssignmentDto assignmentDto, UserDetails userDetails) {
        Course course = courseService.findById(assignmentDto.getCourseId());
        Lesson lesson = lessonService.getLessonById(assignmentDto.getLessonId());

        if(!course.getTeacher().getUser().getEmail().equals(userDetails.getUsername()))
            throw new RuntimeException("You can not add assignment for this course");

        if(!lesson.getCourse().getTeacher().getUser().getUsername().equals(userDetails.getUsername()))
            throw new RuntimeException("You can not add assignment for this course");



        Assignment assignment = Assignment.builder()
                .title(assignmentDto.getTitle())
                .course(course)
                .lesson(lesson)
                .deadline(assignmentDto.getDeadline())
                .description(assignmentDto.getDescription())
                .assignmentType(assignmentDto.getAssignmentType())
                .build();
        return assignmentRepository.save(assignment);
    }


    public Assignment findById(Long id) {
        return assignmentRepository.findById(id).orElse(null);
    }
}
