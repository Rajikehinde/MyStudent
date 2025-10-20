package com.My_Student.service;

import com.My_Student.dto.StudentRequestDto;
import com.My_Student.dto.Response;
import org.springframework.data.domain.Page;

import java.util.List;


public interface ServiceInterface {

    Response registerStudent(StudentRequestDto candidateInformationStudentRequestDto);


    Page<Response> listStudents(String field);

    List<Response> listAllStudents();

    Response searchForStudent(Long id);

    Response updateStudentInformation (StudentRequestDto studentStudentRequestDtoInformation);

    Response deleteStudent (Long id);
}
