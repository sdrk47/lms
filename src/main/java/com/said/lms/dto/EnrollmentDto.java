package com.said.lms.dto;

import com.said.lms.model.User;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class EnrollmentDto {
    private List<User> users;
}
