package com.My_Student.controller;

import com.My_Student.dto.Request;
import com.My_Student.dto.Response;
import com.My_Student.service.ServiceImplementation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api")
public class StudentController {

    @Autowired
    private final ServiceImplementation serviceImplementation;
    public StudentController (ServiceImplementation serviceImplementation){
        this.serviceImplementation = serviceImplementation;
    }

    @PostMapping("/studentRegistration")
    public Response registerStudent(Request candidateInformationRequest){
        return serviceImplementation.registerStudent(candidateInformationRequest);
    }

    @GetMapping("/all/students{field}")
    Page<Response> listAllStudents(@PathVariable String field){
        Page<Response> allStudents = serviceImplementation.listStudents(field);
        return allStudents;
    }

    @GetMapping("/student/{userFetching}")
    public Response searchForStudent(@PathVariable("userFetching") Long id){
        return serviceImplementation.searchForStudent(id);
    }
}