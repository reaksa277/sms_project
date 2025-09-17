package com.example.sms_project.repository;


import com.example.sms_project.entity.Parent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ParentRepository extends JpaRepository <Parent, Long>{
    Boolean existsByEmail(String email);
    Boolean existsByParentName(String phoneNumber);
}
