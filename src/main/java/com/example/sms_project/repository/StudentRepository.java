package com.example.sms_project.repository;

import com.example.sms_project.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {

    Boolean existsByStudentName(String studentName);
    Boolean existsByEmail(String email);

    @Query("SELECT s FROM Student s " +
            "WHERE (:name IS NULL OR LOWER(s.studentName) LIKE LOWER(CONCAT('%', :name, '%'))) " +
            "AND (:id IS NULL OR s.id = :id) " +
            "AND (:email IS NULL OR LOWER(s.email) LIKE LOWER(CONCAT('%', :email, '%')))")
    List<Student> searchStudents(@Param("name") String name,
                                 @Param("id") Long id,
                                 @Param("email") String email);
}
