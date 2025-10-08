package com.My_Student.service;

import com.My_Student.dto.DataView;
import com.My_Student.dto.Request;
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
    public Response registerStudent(Request candidateInformationRequest) {

        Boolean studentProfileExists = studentRepository.existsByEmail(candidateInformationRequest.getEmail());
        if (studentProfileExists) {
            return Response.builder()
                    .responseCode(Utils.EXISTS_CODE)
                    .responseMessage(Utils.EXISTS_MESSAGE)
                    .build();
        }else {

            Student createStudentProfile = Student.builder()
                    .surName(candidateInformationRequest.getSurName())
                    .middleName(candidateInformationRequest.getMiddleName())
                    .firstName(candidateInformationRequest.getFirstName())
                    .dateOfBirth(candidateInformationRequest.getDateOfBirth())
                    .email(candidateInformationRequest.getEmail())
                    .phoneNumber(candidateInformationRequest.getPhoneNumber())
                    .gender(gender(Gender.valueOf(candidateInformationRequest.getGender().toString())))
                    .build();

            int age = ageCalculator(createStudentProfile);
            createStudentProfile.setAge(age);


            log.info("Received date of birth: {}", candidateInformationRequest.getDateOfBirth());

            Student student = studentRepository.save(createStudentProfile);
//            log.info("Check if my request gets here");
            return Response.builder()
                    .responseCode(Utils.SUCCESS_CODE)
                    .responseMessage(Utils.SUCCESS_MESSAGE)
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
    public List<Response> listAllStudents() {
        List<Student> studentLists = studentRepository.findAll();

        List<Response> responseList = new ArrayList<>();
        for (Student student : studentLists) {
            responseList.add(Response.builder()
                    .responseCode(Utils.SUCCESS_CODE)
                    .responseMessage(Utils.SUCCESS_MESSAGE)
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
                .responseCode(Utils.EXISTS_CODE)
                .responseMessage(Utils.EXISTS_MESSAGE)
                .dataView(DataView.builder()
                        .name(student.getSurName() + " " + student.getMiddleName() + " " + student.getFirstName())
                        .build())
                .build();
    }

    @Override
    public Response updateStudentInformation(Request studentRequestInformation) {
        Boolean checkStudentInDatabase = studentRepository.existsByEmail(studentRequestInformation.getEmail());

        if (!checkStudentInDatabase){
            return Response.builder()
                    .responseCode(Utils.NOT_EXISTS_CODE)
                    .responseMessage(Utils.NOT_EXISTS_MESSAGE)
                    .build();
        }
        Student student = Student.builder()
                .surName(studentRequestInformation.getSurName())
                .middleName(studentRequestInformation.getMiddleName())
                .firstName(studentRequestInformation.getFirstName())
                .email(studentRequestInformation.getEmail())
                .age(studentRequestInformation.getAge())
                .dateOfBirth(studentRequestInformation.getDateOfBirth())
                .phoneNumber(studentRequestInformation.getPhoneNumber())
                .gender(gender(Gender.valueOf(studentRequestInformation.getGender().toString())))
                .age(age())
                .build();
        Student updatedProfile = studentRepository.save(student);

        return Response.builder()
                .responseCode(Utils.UPDATED_CODE)
                .responseMessage(Utils.UPDATED_MESSAGE)
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
                .responseCode(Utils.DELETE_CODE)
                .responseMessage(Utils.DELETE_MESSAGE)
                .build();
    }

    @Override
    public int ageCalculator(Student student) {

        System.out.println("Student object: " + student);
        System.out.println("Date of birth: " + (student != null ? student.getDateOfBirth() : "null"));


        if (student == null || student.getDateOfBirth() == null) {
            throw new IllegalArgumentException("Invalid student or year of birth");
        }

        LocalDate birthDate = student.getDateOfBirth();
        LocalDate currentDate = LocalDate.now();

        int age = Period.between(birthDate, currentDate).getYears();
        student.setAge(age);

        return age;
    }

    @Override
    public int age() {
        return ageCalculator(student);
    }

    public Gender gender(Gender gender) {
        if (gender.toString().equalsIgnoreCase(Gender.MALE.toString())) {
            return Gender.MALE;
        } else if (gender.toString().equalsIgnoreCase(Gender.FEMALE.toString())) {
            return Gender.FEMALE;
        } else {
            return null;
        }
    }
}
