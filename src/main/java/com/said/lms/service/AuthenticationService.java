package com.said.lms.service;

import com.said.lms.dto.RegisterDto;
import com.said.lms.enums.Role;
import com.said.lms.model.Teacher;
import com.said.lms.model.User;
import com.said.lms.repository.AuthenticationRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationService implements AuthenticationRepository {

    private final PasswordEncoder passwordEncoder;
    private final UserService userService;
    private final TeacherService teacherService;
    private final BasketService basketService;

    @Override
    @Transactional
    public User register(RegisterDto registerDto) {
        User user = User.builder()
                .firstname(registerDto.getFirstname())
                .lastname(registerDto.getLastname())
                .email(registerDto.getEmail())
                .phoneNumber(registerDto.getPhoneNumber())
                .birthdayDate(registerDto.getBirthdayDate())
                .password(passwordEncoder.encode(registerDto.getPassword()))
                .roles(registerDto.getRoles())
                .build();

        user = userService.save(user);
        if(registerDto.getRoles().name().equals(Role.TEACHER.name())){
            Teacher teacher = Teacher.builder()
                    .position(null)
                    .workExperience(null)
                    .user(user)
                    .build();
            teacherService.create(teacher);
        }

        basketService.create(user);
        return user;
    }
}
