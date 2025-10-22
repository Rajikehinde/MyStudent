package com.My_Student.controller;

import com.My_Student.dto.DataView;
import com.My_Student.dto.StudentRequestDto;
import com.My_Student.dto.Response;
import com.My_Student.service.ServiceImplementation;
import com.My_Student.utitlities.Utils;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(
        name = "CRUD REST APIs for Student Management",
        description = "CRUD REST APIs to CREATE, UPDATE, FETCH AND DELETE student details"
)
@RestController
@RequestMapping(path = "api/student/", produces = {MediaType.APPLICATION_JSON_VALUE})
@Validated
public class StudentController {

    @Autowired
    private final ServiceImplementation serviceImplementation;
    public StudentController (ServiceImplementation serviceImplementation){
        this.serviceImplementation = serviceImplementation;
    }

    @Operation(
            summary = "Student Registration REST API",
            description = "REST API to register new Student"
    )
    @ApiResponse(
            responseCode = "201",
            description = "HTTP Status CREATED"
    )
    @PostMapping("/Registration")
    public ResponseEntity<Response> registerStudent(@Valid @RequestBody StudentRequestDto candidateInformationStudentRequestDto){
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(new Response(Utils.STATUS_201, Utils.MESSAGE_201, DataView.builder().build()));
    }

    @Operation(
            summary = "List All Students REST API",
            description = "REST API to list all registered Students"
    )
    @ApiResponse(
            responseCode = "200",
            description = "HTTP Status OK"
    )
    @GetMapping("/all/students{field}")
    Page<Response> listAllStudents(@PathVariable String field){
        Page<Response> allStudents = serviceImplementation.listStudents(field);
        return allStudents;
    }

    @Operation(
            summary = "List All Students REST API",
            description = "REST API to list all registered Students"
    )
    @ApiResponse(
            responseCode = "200",
            description = "HTTP Status OK"
    )
    @GetMapping("/all/stu")
    public List<Response> listAllStudents(){
        return serviceImplementation.listAllStudents();
    }

    @Operation(
            summary = "Search Student REST API",
            description = "REST API to search a registered Student by ID"
    )
    @ApiResponse(
            responseCode = "200",
            description = "HTTP Status OK"
    )
    @GetMapping("/student/{userFetching}")
    public Response searchForStudent(@PathVariable("userFetching") Long id){
        return serviceImplementation.searchForStudent(id);
    }

    @Operation(
            summary = "Update Student Information REST API",
            description = "REST API to update existing Student information"
    )
    @ApiResponse(
            responseCode = "200",
            description = "HTTP Status OK"
    )
    @PostMapping("update")
    public Response updateStudents(@Valid @RequestBody StudentRequestDto studentStudentRequestDtoInformation){
        return serviceImplementation.updateStudentInformation(studentStudentRequestDtoInformation);
    }

    @Operation(
            summary = "Delete Student REST API",
            description = "REST API to delete a registered Student by ID"
    )
    @ApiResponse(
            responseCode = "200",
            description = "HTTP Status OK"
    )
    @DeleteMapping("delete")
    public Response deleteStudent (Long id){
        return serviceImplementation.deleteStudent(id);
    }
}