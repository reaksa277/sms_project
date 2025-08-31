package com.example.sms_project.dto.enrollment;

import lombok.Data;

import java.util.Date;

@Data
public class EnrollmentResponseDto {
    private Long enrollmentId;
    private Date enrollmentDate;
    private String enrollmentStatus;
    private Long studentId;
    private Long courseId;
}
