package com.My_Student.service;

import com.My_Student.dto.Request;
import com.My_Student.dto.Response;
import com.My_Student.entity.Student;
import org.springframework.data.domain.Page;

import java.util.List;


public interface ServiceInterface {

    Response registerStudent(Request candidateInformationRequest);


    Page<Response> listStudents(String field);

    List<Response> listAllStudents();

    Response searchForStudent(Long id);

    Response updateStudentInformation (Request studentRequestInformation);

    Response deleteStudent (Long id);

    int ageCalculator(Student student);

    int age();
}
