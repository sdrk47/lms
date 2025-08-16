package com.said.lms.service;

import com.said.lms.dto.UserUpdateDto;
import com.said.lms.model.User;
import com.said.lms.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public User save(User user) {
        return userRepository.save(user);
    }

    public User findByEmail(String email) {
        return userRepository.findUserByEmail(email);
    }

    public User findById(Long userId) {
        return userRepository.findById(userId).orElse(null);
    }

    public User update(UserUpdateDto userUpdateDto, UserDetails userDetails) {
        User user = userRepository.findUserByEmail(userDetails.getUsername());
        user.setLastname(userUpdateDto.getLastname());
        user.setFirstname(userUpdateDto.getFirstname());
        user.setBirthdayDate(user.getBirthdayDate());

        return userRepository.save(user);
    }
}
