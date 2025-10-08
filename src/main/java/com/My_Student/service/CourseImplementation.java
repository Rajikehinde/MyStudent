//package com.My_Student.service;
//
//import com.My_Student.dto.CourseRequest;
//import com.My_Student.dto.CourseResponse;
//import com.My_Student.entity.Course;
//import com.My_Student.repository.CourseRepository;
//import org.springframework.stereotype.Service;
//
//import java.util.Optional;
//
//@Service
//public class CourseImplementation implements CourseInterface{
//
//    private final CourseRepository courseRepository;
//
//    public CourseImplementation(CourseRepository courseRepository) {
//        this.courseRepository = courseRepository;
//    }
//
//    @Override
//    public CourseResponse register(CourseRequest request) {
//
//       Optional<Course> checkCourseInDatabase = courseRepository.findCourseByName(request.getCourseName());
//       return s
//    }
//}
