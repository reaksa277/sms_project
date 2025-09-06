package com.example.sms_project.mapper;

import com.example.sms_project.dto.enrollment.EnrollmentDto;
import com.example.sms_project.entity.Enrollment;
import com.example.sms_project.entity.Student;
import org.springframework.stereotype.Component;

@Component
public class EnrollmentMapper {

    public Enrollment toEntity(EnrollmentDto dto, Student student) {
        Enrollment entity = new Enrollment();

        entity.setEnrollmentDate(dto.getEnrollmentDate());
        entity.setStudent(student);

        return entity;
    }
}
