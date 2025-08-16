package com.said.lms.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.said.lms.enums.Role;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class RegisterDto {

    private String email;

    private String firstname;

    private String lastname;

    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate birthdayDate;

    private String password;

    private String phoneNumber;

    private Role roles;
}
