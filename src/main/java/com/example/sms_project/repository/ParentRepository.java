package com.example.sms_project.repository;

import com.example.sms_project.entity.Parent;
import com.example.sms_project.entity.Student;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ParentRepository extends JpaRepository <Parent, Long>{
    Boolean existsByEmail(String email);
    Boolean existsByParentName(String phoneNumber);
    
    @Query("SELECT p FROM Parent p WHERE " +
       "(:parentName IS NULL OR p.parentName LIKE %:parentName%) AND " +
       "(:email IS NULL OR p.email LIKE %:email%) AND " +
       "(:relationship IS NULL OR p.relationship = :relationship) AND " +
       "(:studentId IS NULL OR p.student.id = :studentId)")
    List<Parent> searchParents(
        @Param("parentName") String parentName,
        @Param("email") String email,
        @Param("relationship") String relationship,
        @Param("studentId") Long studentId
    );

    List<Student> findByStudentIdIn(List<Long> studentIds, Sort createdAt);

}
