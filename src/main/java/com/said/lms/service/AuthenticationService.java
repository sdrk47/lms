package com.said.lms.service;

import com.said.lms.dto.LoginDto;
import com.said.lms.token.dto.RefreshTokenRequestDto;
import com.said.lms.dto.RegisterDto;
import com.said.lms.enums.Role;
import com.said.lms.exception.IncorrectPasswordException;
import com.said.lms.model.Teacher;
import com.said.lms.model.User;
import com.said.lms.repository.AuthenticationRepository;
import com.said.lms.token.repository.JwtRepository;
import com.said.lms.token.JwtParser;
import com.said.lms.token.dto.JwtAuthenticationResponseDto;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.MalformedJwtException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;

@Service
@RequiredArgsConstructor
public class AuthenticationService implements AuthenticationRepository {

    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final UserService userService;
    private final TeacherService teacherService;
    private final BasketService basketService;
    private final JwtRepository jwtRepository;
    private final JwtParser jwtParser;

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

    @Override
    public JwtAuthenticationResponseDto login(LoginDto loginDto) {
        UserDetails user = userService.findByEmail(loginDto.getEmail());

        if(user == null) {
            throw new UsernameNotFoundException("User not found");
        }

        if(!passwordEncoder.matches(loginDto.getPassword(), user.getPassword())) {
            throw new IncorrectPasswordException("Authentication failed");
        }

        Authentication auth = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginDto.getEmail(), loginDto.getPassword())
        );

        SecurityContextHolder.getContext().setAuthentication(auth);

        String jwt = jwtRepository.generateToken(user);
        String refreshToken = jwtRepository.generateRefreshToken(new HashMap<>(), user);

        return JwtAuthenticationResponseDto.builder()
                .timestamp(new Date())
                .username(user.getUsername())
                .accessToken(jwt)
                .refreshToken(refreshToken)
                .build();
    }

    @Override
    public JwtAuthenticationResponseDto refresh(RefreshTokenRequestDto refreshTokenRequestDto) {
        String email;

        try {
            email = jwtParser.extractUsername(refreshTokenRequestDto.getRefreshToken());
        } catch (ExpiredJwtException | MalformedJwtException ex) {
            throw new RuntimeException("Invalid refresh token");
        }

        User user = userService.findByEmail(email);
        return generateResponse(user, refreshTokenRequestDto.getRefreshToken());
    }

    private JwtAuthenticationResponseDto generateResponse(User user, String refreshToken) {
        String jwt = jwtRepository.generateToken(user);
        return JwtAuthenticationResponseDto.builder()
                .username(user.getUsername())
                .timestamp(new Date())
                .accessToken(jwt)
                .refreshToken(refreshToken)
                .build();
    }

    private void authenticateUser(String email, String password) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(email, password)
        );
    }
}
