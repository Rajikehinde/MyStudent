package com.My_Student.service;

import com.My_Student.dto.DataView;
import com.My_Student.dto.StudentRequestDto;
import com.My_Student.dto.Response;
import com.My_Student.entity.Gender;
import com.My_Student.entity.Student;
import com.My_Student.repository.StudentRepository;
import com.My_Student.utitlities.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.slf4j.*;

import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.List;

@Service
public class ServiceImplementation implements ServiceInterface{

    private Student student;

    private final Logger log = LoggerFactory.getLogger(getClass());

    private final StudentRepository studentRepository;

    @Autowired
    public ServiceImplementation(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    @Override
    public Response registerStudent(StudentRequestDto candidateInformationStudentRequestDto) {

        Boolean studentProfileExists = studentRepository.existsByEmail(candidateInformationStudentRequestDto.getEmail());
        if (studentProfileExists) {
            return Response.builder()
                    .responseCode(Utils.STATUS_409)
                    .responseMessage(Utils.MESSAGE_409)
                    .build();
        }else {
            Student createStudentProfile = Student.builder()
                    .surName(candidateInformationStudentRequestDto.getSurName())
                    .middleName(candidateInformationStudentRequestDto.getMiddleName())
                    .firstName(candidateInformationStudentRequestDto.getFirstName())
                    .dateOfBirth(candidateInformationStudentRequestDto.getDateOfBirth())
                    .email(candidateInformationStudentRequestDto.getEmail())
                    .phoneNumber(candidateInformationStudentRequestDto.getPhoneNumber())
                    .gender(gender(Gender.valueOf(candidateInformationStudentRequestDto.getGender().toString())))
                    .build();
            int age = ageCalculator(createStudentProfile);
            createStudentProfile.setAge(age);

            Student student = studentRepository.save(createStudentProfile);
            return Response.builder()
                    .responseCode(Utils.STATUS_201)
                    .responseMessage(Utils.MESSAGE_201)
                    .dataView(DataView.builder()
                            .name(student.getSurName() + " " + student.getMiddleName() + " " + student.getFirstName())
                            .build())
                    .build();
        }
    }


    @Override
    public Page<Response> listStudents(String field) {
        Page<Student> studentLists = studentRepository.findAll((Pageable) Sort.by(Sort.Direction.ASC, field));

        List<Response> responseList = new ArrayList<>();
        for(Student student: studentLists){
            responseList.add(Response.builder()
                            .responseCode(Utils.STATUS_200)
                            .responseMessage(Utils.MESSAGE_200)
                            .dataView(DataView.builder()
                                    .name(student.getSurName() + " " + student.getMiddleName() + " " + student.getFirstName())
                                    .build())
                    .build());
        }
        return (Page<Response>) responseList;
    }

    @Override
    public List<Response> listAllStudents() {
        List<Student> studentLists = studentRepository.findAll();

        List<Response> responseList = new ArrayList<>();
        for (Student student : studentLists) {
            responseList.add(Response.builder()
                    .responseCode(Utils.STATUS_200)
                    .responseMessage(Utils.MESSAGE_200)
                    .dataView(DataView.builder()
                            .name(student.getSurName() + " " + student.getMiddleName() + " " + student.getFirstName() + " " + student.getGender() + " " + student.getAge())
                            .build())
                    .build());
        }
        return responseList;
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
                .responseCode(Utils.STATUS_200)
                .responseMessage(Utils.MESSAGE_200)
                .dataView(DataView.builder()
                        .name(student.getSurName() + " " + student.getMiddleName() + " " + student.getFirstName())
                        .build())
                .build();
    }

    @Override
    public Response updateStudentInformation(StudentRequestDto studentStudentRequestDtoInformation) {
        Boolean checkStudentInDatabase = studentRepository.existsByEmail(studentStudentRequestDtoInformation.getEmail());

        if (!checkStudentInDatabase){
            return Response.builder()
                    .responseCode(Utils.NOT_EXISTS_CODE)
                    .responseMessage(Utils.NOT_EXISTS_MESSAGE)
                    .build();
        }
        Student student = Student.builder()
                .surName(studentStudentRequestDtoInformation.getSurName())
                .middleName(studentStudentRequestDtoInformation.getMiddleName())
                .firstName(studentStudentRequestDtoInformation.getFirstName())
                .email(studentStudentRequestDtoInformation.getEmail())
                .age(studentStudentRequestDtoInformation.getAge())
                .dateOfBirth(studentStudentRequestDtoInformation.getDateOfBirth())
                .phoneNumber(studentStudentRequestDtoInformation.getPhoneNumber())
                .gender(gender(Gender.valueOf(studentStudentRequestDtoInformation.getGender().toString())))
                .age(age())
                .build();
        Student updatedProfile = studentRepository.save(student);

        return Response.builder()
                .responseCode(Utils.STATUS_201)
                .responseMessage(Utils.MESSAGE_201)
                .dataView(DataView.builder()
                        .name(updatedProfile.getSurName() + " " + updatedProfile.getFirstName() + " " + updatedProfile.getFirstName())
                        .build())
                .build();
    }

    @Override
    public Response deleteStudent(Long id) {
        Boolean studentInDatabase = studentRepository.existsByEmail(String.valueOf(id));

        if (!studentInDatabase){
            return Response.builder()
                    .responseCode(Utils.NOT_EXISTS_CODE)
                    .responseMessage(Utils.NOT_EXISTS_MESSAGE)
                    .build();
        }

//        Student student = studentRepository.delete(id).get();
        return Response.builder()
                .responseCode(Utils.STATUS_201)
                .responseMessage(Utils.MESSAGE_201)
                .build();
    }

    private int ageCalculator(Student student) {

        if (student == null || student.getDateOfBirth() == null) {
            throw new IllegalArgumentException("Invalid student or year of birth");
        }

        LocalDate birthDate = student.getDateOfBirth();
        LocalDate currentDate = LocalDate.now();

        int age = Period.between(birthDate, currentDate).getYears();
        student.setAge(age);

        return age;
    }
    private int age() {
        return ageCalculator(student);
    }

    private Gender gender(Gender gender) {
        if (gender.toString().equalsIgnoreCase(Gender.MALE.toString())) {
            return Gender.MALE;
        } else if (gender.toString().equalsIgnoreCase(Gender.FEMALE.toString())) {
            return Gender.FEMALE;
        } else {
            return null;
        }
    }
}
