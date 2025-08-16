package com.said.lms.controller;

import com.said.lms.dto.StudentAssignmentDto;
import com.said.lms.model.StudentAssignment;
import com.said.lms.service.StudentAssignmentService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/student-assignment")
public class StudentAssignmentController {

    private final StudentAssignmentService studentAssignmentService;

    @PostMapping("/create-assignment-for-users")
    public StudentAssignment createAssignmentForUsers(
            @AuthenticationPrincipal UserDetails userDetails,
            @RequestBody StudentAssignmentDto studentAssignmentDto
    ) {
        return studentAssignmentService.save(studentAssignmentDto, userDetails);
    }
}
