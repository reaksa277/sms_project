package com.example.sms_project.repository;

import com.example.sms_project.entity.Enrollment;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EnrollmentRepository extends JpaRepository<Enrollment,Long> {
    // SELECT * from enrollment where student_id IN (1, 3) ORDER BY <sorting_param>
    List<Enrollment> findByStudentIdIn(List<Long> studentIds, Sort createdAt);
    List<Enrollment> findByCourseIdIn(List<Long> courseIds, Sort createdAt);
}
