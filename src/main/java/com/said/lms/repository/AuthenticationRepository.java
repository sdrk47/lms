package com.said.lms.repository;

import com.said.lms.dto.RegisterDto;
import com.said.lms.model.User;
import org.springframework.stereotype.Repository;

@Repository
public interface AuthenticationRepository {
    User register(RegisterDto registerDto) ;
}
