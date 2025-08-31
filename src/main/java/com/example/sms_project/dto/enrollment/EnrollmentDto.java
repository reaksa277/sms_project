package com.example.sms_project.dto.enrollment;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EnrollmentDto {
    private LocalDate enrollmentDate;
    private String enrollmentStatus = "ACTIVE";
    private Long studentId;
    private Long courseId;
}
