package com.My_Student.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "student")
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long StudentId;
    private String firstName;
    private String surName;
    private String middleName;
    private String studentName;
    private String password;
    private int year;
    private int age;
    private Roles roles;
    private int phoneNumber;
    private Gender gender;
    private String email;
    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(name = "student_department",
            joinColumns = @JoinColumn(name = "student_id", referencedColumnName = "StudentId"),
            inverseJoinColumns = @JoinColumn(name = "department_id", referencedColumnName = "departmentId"))
    private Department department;

    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(name = "student_course",
            joinColumns = @JoinColumn(name = "student_id", referencedColumnName = "StudentId"),
            inverseJoinColumns = @JoinColumn(name = "course_id", referencedColumnName = "courseId"))
    private List<Course> courses;
}
