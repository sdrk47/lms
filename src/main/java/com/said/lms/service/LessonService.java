package com.said.lms.service;

import com.said.lms.dto.LessonDto;
import com.said.lms.model.Course;
import com.said.lms.model.Lesson;
import com.said.lms.repository.LessonRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
@RequiredArgsConstructor
public class LessonService {
    private final LessonRepository lessonRepository;
    private final CourseService courseService;

    public Lesson create(UserDetails userDetails, LessonDto lessonDto){
        Course course = courseService.findById(lessonDto.getCourseId());

        if(!course.getTeacher().getUser().getEmail().equals(userDetails.getUsername()))
            throw new RuntimeException("You can not add lesson for this course");

        return lessonRepository.save(Lesson.builder()
                .title(lessonDto.getTitle())
                .description(lessonDto.getDescription())
                .content(lessonDto.getContent())
                .course(course)
                .build());

    }

    public List<Lesson> getLessonsOfCourse(Long courseId) {
        List<Lesson> lessonsInCourse = lessonRepository.findByCourseCourseId(courseId);
        if(lessonsInCourse.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Lesson not found");
        }
        return lessonRepository.findByCourseCourseId(courseId);
    }

    public Lesson getLessonById(Long id) {
        return lessonRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Lesson not found"));
    }
}
