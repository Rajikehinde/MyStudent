package com.My_Student.dto;

import com.My_Student.entity.Gender;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class StudentRequestDto {

    @Size(min = 5, max = 30, message = "The length of the first name should be between 5 and 30")
    @NotNull(message = "Firstname cannot be null or empty!")
    private String firstName;

    @Size(min = 5, max = 30, message = "The length of the  Surname should be between 5 and 30")
    @NotNull(message = "Surname cannot be null or empty!")
    private String surName;

    @Size(min = 5, max = 30, message = "The length of the Middle name should be between 5 and 30")
    @NotNull(message = "Middle name cannot be null or empty!")
    private String middleName;

    @NotNull(message = "Date of birth cannot be null or empty!")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate dateOfBirth;

    private int age;

    @NotNull(message = "Gender is required")
    private Gender gender;

    @NotNull(message = "Email cannot be null or empty")
    @Email(message = "Email address should be valid")
    private String email;

    @Pattern(regexp = "(^$|[0-9]{10})", message = "Mobile number must be 10 digits")
    private int phoneNumber;
    private String courseName;

}
