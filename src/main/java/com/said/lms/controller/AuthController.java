package com.said.lms.controller;

import com.said.lms.dto.RegisterDto;
import com.said.lms.model.User;
import com.said.lms.repository.AuthenticationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/auth")
public class AuthController {

    private final AuthenticationRepository authenticationRepository;

    @PostMapping("/register")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<User> register(@RequestBody RegisterDto registerDto){
        return ResponseEntity.ok(authenticationRepository.register(registerDto));
    }
}
