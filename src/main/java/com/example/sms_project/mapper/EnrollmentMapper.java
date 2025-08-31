package com.example.sms_project.mapper;

import com.example.sms_project.dto.enrollment.EnrollmentDto;
import com.example.sms_project.entity.Enrollment;
import org.springframework.stereotype.Component;

@Component
public class EnrollmentMapper {

    public Enrollment toEntity(EnrollmentDto dto) {
        Enrollment enrollment = new Enrollment();

        enrollment.setEnrollmentDate(dto.getEnrollmentDate());

        return enrollment;
    }
}
