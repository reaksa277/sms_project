package com.example.sms_project.repository;

import com.example.sms_project.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {

    Boolean existsByStudentName(String studentName);
    Boolean existsByEmail(String email);
}
