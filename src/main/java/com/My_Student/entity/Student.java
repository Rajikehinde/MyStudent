package com.My_Student.entity;

import jakarta.persistence.*;
import lombok.*;
import org.apache.catalina.core.ApplicationContext;
import org.apache.catalina.core.StandardContext;

import java.time.LocalDate;
import java.util.List;

@Entity
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "student")
public class Student extends BaseEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long StudentId;
    private String firstName;
    private String surName;
    private String middleName;
    private LocalDate dateOfBirth;
    private int age;
    private int phoneNumber;
    private Gender gender;
    private String email;
}
