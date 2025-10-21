package com.My_Student.controller;

import com.My_Student.dto.DataView;
import com.My_Student.dto.StudentRequestDto;
import com.My_Student.dto.Response;
import com.My_Student.service.ServiceImplementation;
import com.My_Student.utitlities.Utils;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping(path = "api/student/", produces = {MediaType.APPLICATION_JSON_VALUE})
@Validated
public class StudentController {

    @Autowired
    private final ServiceImplementation serviceImplementation;
    public StudentController (ServiceImplementation serviceImplementation){
        this.serviceImplementation = serviceImplementation;
    }

    @PostMapping("/Registration")
    public ResponseEntity<Response> registerStudent(@Valid @RequestBody StudentRequestDto candidateInformationStudentRequestDto){
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(new Response(Utils.STATUS_201, Utils.MESSAGE_201, DataView.builder().build()));
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
    public Response updateStudents(@Valid @RequestBody StudentRequestDto studentStudentRequestDtoInformation){
        return serviceImplementation.updateStudentInformation(studentStudentRequestDtoInformation);
    }

    @DeleteMapping("delete")
    public Response deleteStudent (Long id){
        return serviceImplementation.deleteStudent(id);
    }
}