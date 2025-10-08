package com.My_Student.controller;

import com.My_Student.dto.Request;
import com.My_Student.dto.Response;
import com.My_Student.service.ServiceImplementation;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/v1/student")
public class StudentController {

    @Autowired
    private final ServiceImplementation serviceImplementation;
    public StudentController (ServiceImplementation serviceImplementation){
        this.serviceImplementation = serviceImplementation;
    }

    @PostMapping("/Registration")
    public Response registerStudent(@Valid @RequestBody Request candidateInformationRequest){
        return serviceImplementation.registerStudent(candidateInformationRequest);
    }

    @GetMapping("/all/students{field}")
    Page<Response> listAllStudents(@PathVariable String field){
        Page<Response> allStudents = serviceImplementation.listStudents(field);
        return allStudents;
    }
    @GetMapping("/all/stu")
    public List<Response> listAllStudents(){
        return serviceImplementation.listAllStudents();
    }

    @GetMapping("/student/{userFetching}")
    public Response searchForStudent(@PathVariable("userFetching") Long id){
        return serviceImplementation.searchForStudent(id);
    }

    @PostMapping("update")
    public Response updateStudents(@RequestBody Request studentRequestInformation){
        return serviceImplementation.updateStudentInformation(studentRequestInformation);
    }

    @DeleteMapping("delete")
    public Response deleteStudent (Long id){
        return serviceImplementation.deleteStudent(id);
    }
}