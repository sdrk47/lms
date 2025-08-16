package com.said.lms.repository;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Repository;

import java.util.Map;

@Repository
public interface JwtRepository {
    String generateToken(UserDetails userDetails);
    String generateRefreshToken(Map<String, Object> extraClaim, UserDetails userDetails);
}
