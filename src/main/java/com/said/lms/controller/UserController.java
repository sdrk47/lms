package com.said.lms.controller;

import com.said.lms.dto.UserUpdateDto;
import com.said.lms.model.User;
import com.said.lms.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/users")
public class UserController {
    private final UserService userService;

    @GetMapping("/info")
    public User getAllInfo(@AuthenticationPrincipal UserDetails userDetails) {
        return userService.findByEmail(userDetails.getUsername());
    }

    @PutMapping("/update")
    public User update(@AuthenticationPrincipal UserDetails userDetails,
                       @RequestBody UserUpdateDto userUpdateDto) {
        return userService.update(userUpdateDto, userDetails);
    }
}
