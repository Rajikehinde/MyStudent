package com.My_Student.repository;

import com.My_Student.entity.Gender;
import com.My_Student.entity.Student;
import jakarta.persistence.Id;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public interface StudentRepository extends JpaRepository<Student,Long> {

    @Query("SELECT s FROM Student s")
    List<Student> listOfStudents ();

    List<Student> findByGender(Gender gender);

    @Query("SELECT CASE WHEN COUNT(s) > 0 THEN true ELSE false END FROM Student s WHERE s.id = ?1")
    Boolean findStudentById (Long studentId);
    Optional<Student> findByStudentName(String StudentName);

    Boolean existsByEmail(String email);

    Page<Student> findAll(Pageable pageable);

//    Student searchForStudent (String student);
}
