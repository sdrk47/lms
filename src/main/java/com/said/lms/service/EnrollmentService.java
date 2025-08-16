package com.said.lms.service;

import com.said.lms.enums.CourseStatus;
import com.said.lms.model.Course;
import com.said.lms.model.Enrollment;
import com.said.lms.model.User;
import com.said.lms.repository.EnrollmentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class EnrollmentService {

    private final EnrollmentRepository enrollmentRepository;
    private final UserService userService;
    private final CourseService courseService;

    public Enrollment createEnrollment(UserDetails userDetails, Long courseId) {
        Course course = courseService.findById(courseId);
        User user = userService.findByEmail(userDetails.getUsername());

        Optional<Enrollment> existingEnrollment = enrollmentRepository.findByUserAndCourse(user, course);
        if(existingEnrollment.isPresent()) {
            throw new RuntimeException("user is already enrolled to this course");
        }
        Enrollment enrollment = Enrollment.builder()
                .course(course)
                .user(user)
                .createdAt(LocalDateTime.now())
                .status(CourseStatus.START)
                .build();

        return enrollmentRepository.save(enrollment);
    }
}
