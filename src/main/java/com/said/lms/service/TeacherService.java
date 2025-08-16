package com.said.lms.service;

import com.said.lms.model.Teacher;
import com.said.lms.repository.TeacherRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TeacherService {

    private final TeacherRepository teacherRepository;

    public void create(Teacher teacher) {
        teacherRepository.save(teacher);
    }
}
