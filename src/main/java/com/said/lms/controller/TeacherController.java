package com.said.lms.controller;

import com.said.lms.dto.UpdateTeacherDto;
import com.said.lms.model.Teacher;
import com.said.lms.service.TeacherService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/teachers")
public class TeacherController {
    private final TeacherService teacherService;

    @GetMapping("/info")
    public Teacher getTeacher(@AuthenticationPrincipal UserDetails userDetails) {
        return teacherService.getTeacher(userDetails);
    }

    @PutMapping("/update")
    public Teacher updateTeacher(@AuthenticationPrincipal UserDetails userDetails,
                                 @RequestBody UpdateTeacherDto teacherDto
    ) {
        return teacherService.update(userDetails, teacherDto);
    }
}
