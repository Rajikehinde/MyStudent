package com.My_Student.service;

import com.My_Student.dto.DataView;
import com.My_Student.dto.Request;
import com.My_Student.dto.Response;
import com.My_Student.entity.Student;
import com.My_Student.repository.StudentRepository;
import com.My_Student.utitlities.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ServiceImplementation implements ServiceInterface{
    @Autowired
    private final StudentRepository studentRepository;

    public ServiceImplementation(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    @Override
    public Response registerStudent(Request candidateInformationRequest) {

        Boolean studentProfileExists = studentRepository.existsByEmail(candidateInformationRequest.getEmail());
        if (studentProfileExists) {
            return Response.builder()
                    .responseCode(Utils.EXISTS_CODE)
                    .responseMessage(Utils.EXISTS_MESSAGE)
                    .build();
        }

        Student createStudentProfile = Student.builder()
                .firstName(candidateInformationRequest.getFirstName())
                .middleName(candidateInformationRequest.getMiddleName())
                .surName(candidateInformationRequest.getSurName())
                .age(candidateInformationRequest.getAge())
                .email(candidateInformationRequest.getEmail())
                .gender(candidateInformationRequest.getGender())
                .build();
        Student student = studentRepository.save(createStudentProfile);
        return Response.builder()
                .responseCode(Utils.SUCCESS_CODE)
                .responseMessage(Utils.SUCCESS_MESSAGE)
                .dataView(DataView.builder()
                        .name(student.getSurName() + " " + student.getMiddleName() + " " + student.getFirstName())
                        .build())
                .build();
    }


    @Override
    public Page<Response> listStudents(String field) {
        Page<Student> studentList = studentRepository.findAll((Pageable) Sort.by(Sort.Direction.ASC, field));

        List<Response> responseList = new ArrayList<>();
        for(Student student: studentList){
            responseList.add(Response.builder()
                            .responseCode(Utils.SUCCESS_CODE)
                            .responseMessage(Utils.SUCCESS_MESSAGE)
                            .dataView(DataView.builder()
                                    .name(student.getSurName() + " " + student.getMiddleName() + " " + student.getFirstName())
                                    .build())
                    .build());
        }
        return (Page<Response>) responseList;
    }

    @Override
    public Response searchForStudent(Long studentId) {
        if (!studentRepository.findStudentById(studentId)){
            return Response.builder()
                    .responseCode(Utils.NOT_EXISTS_CODE)
                    .responseMessage(Utils.NOT_EXISTS_MESSAGE)
                    .build();
        }
        Student student = studentRepository.findById(studentId).get();
        return Response.builder()
                .responseCode(Utils.EXISTS_CODE)
                .responseMessage(Utils.EXISTS_MESSAGE)
                .dataView(DataView.builder()
                        .name(student.getSurName() + " " + student.getMiddleName() + " " + student.getFirstName())
                        .build())
                .build();
    }

    @Override
    public Response updateStudentInformation(Request StudentRequestInformation) {
        return null;
    }

    @Override
    public Response deleteStudent(Long id) {
        return null;
    }
}
