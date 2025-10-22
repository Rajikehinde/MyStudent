package com.My_Student.dto;

import com.My_Student.entity.Gender;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;
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
@Schema(
        name = "StudentRequest",
        description = "Schema to hold Student  information"
)
public class StudentRequestDto {

    @Schema(
            description = "First Name of the Student ", example = "Kehinde"
    )
    @Size(min = 5, max = 30, message = "The length of the first name should be between 5 and 30")
    @NotNull(message = "Firstname cannot be null or empty!")
    private String firstName;

    @Schema(
            description = "Surname of the Student ", example = "Raji"
    )
    @Size(min = 5, max = 30, message = "The length of the  Surname should be between 5 and 30")
    @NotNull(message = "Surname cannot be null or empty!")
    private String surName;

    @Schema(
            description = "Middle Name of the Student ", example = "Husaini"
    )
    @Size(min = 5, max = 30, message = "The length of the Middle name should be between 5 and 30")
    @NotNull(message = "Middle name cannot be null or empty!")
    private String middleName;

    @Schema(
            description = "Date of Birth of the Student ", example = "1999-08-29"
    )
    @NotNull(message = "Date of birth cannot be null or empty!")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate dateOfBirth;


    private int age;

    @Schema(
            description = "Gender of the Student ", example = "MALE/FEMALE/OTHER"
    )
    @NotNull(message = "Gender is required")
    private Gender gender;

    @Schema(
            description = "Email of the Student ", example = "Kehinderaji28@gmail.com"
    )
    @NotNull(message = "Email cannot be null or empty")
    @Email(message = "Email address should be valid")
    private String email;

    @Pattern(regexp = "(^$|[0-9]{10})", message = "Mobile number must be 10 digits")
    private int phoneNumber;
}
