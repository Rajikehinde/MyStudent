package com.My_Student.dto;

import com.My_Student.entity.Gender;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Request {
    private String firstName;
    private String surName;
    private String middleName;
    private String level;
    private int age;
    private int year;
    private Gender gender;
    private String department;
    private String email;
    private int phoneNumber;
    private String courseName;
    private String description;
    private int credits;
    private String grade;
}
