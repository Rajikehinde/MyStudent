package com.My_Student.service;

import com.My_Student.dto.Request;
import com.My_Student.dto.Response;
import com.My_Student.entity.Student;
import com.My_Student.repository.StudentRepository;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


public interface ServiceInterface {

    Response registerStudent(Request candidateInformationRequest);


    Page<Response> listStudents(String field);

    Response searchForStudent(Long id);

    Response updateStudentInformation (Request StudentRequestInformation);

    Response deleteStudent (Long id);
}
