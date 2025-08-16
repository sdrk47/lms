package com.said.lms.controller;

import com.said.lms.dto.AssignmentDto;
import com.said.lms.model.Assignment;
import com.said.lms.service.AssignmentService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/assignment")
public class AssignmentController {
    private final AssignmentService assignmentService;

    @PostMapping("/create")
    public Assignment createAssignment(@AuthenticationPrincipal UserDetails userDetails,
                                       @RequestBody AssignmentDto assignmentDto
    ) {
        return assignmentService.save(assignmentDto, userDetails);
    }
}
