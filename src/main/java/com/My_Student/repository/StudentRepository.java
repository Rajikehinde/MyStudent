package com.My_Student.repository;

import com.My_Student.dto.Response;
import com.My_Student.entity.Gender;
import com.My_Student.entity.Student;
import jakarta.transaction.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface StudentRepository extends JpaRepository<Student,Long> {

    @Query("SELECT s FROM Student s")
    List<Response> listOfStudents ();

    @Transactional
    @Modifying
    List<Student> findByGender(Gender gender);

    @Query("SELECT CASE WHEN COUNT(s) > 0 THEN true ELSE false END FROM Student s WHERE s.id = ?1")
    @Transactional
    @Modifying
    Boolean findStudentById (Long studentId);

    Boolean existsByEmail(String email);

    Page<Student> findAll(Pageable pageable);

    void deleteById(Long studentId);
}
