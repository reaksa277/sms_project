package com.example.sms_project.dto.enrollment;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EnrollmentDto {
    @NotNull(message = "Enrollment Date is required")
    private LocalDate enrollmentDate;
    private String enrollmentStatus = "ACTIVE";

    @NotNull(message = "student Id is required")
    private Long studentId;

    @NotNull(message = "course Id is required")
    private Long courseId;
}
