package com.said.lms.service;

import com.said.lms.dto.UpdateTeacherDto;
import com.said.lms.exception.TeacherNotFoundException;
import com.said.lms.model.Teacher;
import com.said.lms.model.User;
import com.said.lms.repository.TeacherRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TeacherService {

    private final TeacherRepository teacherRepository;
    private final UserService userService;

    public void create(Teacher teacher) {
        teacherRepository.save(teacher);
    }

    public Teacher getByUserId(Long userId) {
        return teacherRepository.findByUserUserId(userId).orElse(null);
    }

    public Teacher getTeacher(UserDetails userDetails) {
        User user = userService.findByEmail(userDetails.getUsername());

        return teacherRepository.findByUserUserId(user.getUserId()).orElseThrow(() ->
                new TeacherNotFoundException("User Not Found"));
    }

    public Teacher update(UserDetails userDetails, UpdateTeacherDto updateTeacherDto) {
        User user = userService.findByEmail(userDetails.getUsername());
        Teacher teacher = teacherRepository.findByUserUserId(user.getUserId()).orElseThrow(() ->
                new TeacherNotFoundException("User Not Found"));

        teacher.setPosition(updateTeacherDto.getPosition());
        teacher.setWorkExperience(updateTeacherDto.getWorkExperience());

        return teacherRepository.save(teacher);
    }
}
