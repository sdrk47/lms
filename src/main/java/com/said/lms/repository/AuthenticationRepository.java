package com.said.lms.repository;

import com.said.lms.dto.LoginDto;
import com.said.lms.token.dto.RefreshTokenRequestDto;
import com.said.lms.dto.RegisterDto;
import com.said.lms.model.User;
import com.said.lms.token.dto.JwtAuthenticationResponseDto;
import org.springframework.stereotype.Repository;

@Repository
public interface AuthenticationRepository {
    User register(RegisterDto registerDto);
    JwtAuthenticationResponseDto login(LoginDto loginDto);
    JwtAuthenticationResponseDto refresh(RefreshTokenRequestDto refreshTokenRequestDto);
}
