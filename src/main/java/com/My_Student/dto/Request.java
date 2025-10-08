package com.My_Student.dto;

import com.My_Student.entity.Gender;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Request {
    private String firstName;
    private String surName;
    private String middleName;
//    private String level;
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate dateOfBirth;
    private int age;
    @NotNull(message = "Gender is required")
    private Gender gender;
    private String department;
    private String email;
    private int phoneNumber;
    private String courseName;
//    private String description;
//    private int credits;
//    private String grade;
}
