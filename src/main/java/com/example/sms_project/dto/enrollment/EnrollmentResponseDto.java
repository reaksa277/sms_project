package com.example.sms_project.dto.enrollment;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

@Data
@JsonPropertyOrder({"enrollment_id", "student_id", "course_id", "enrollment_date", "enrollment_status", "created_at", "updated_at"})
public class EnrollmentResponseDto {
    @JsonProperty("enrollment_id")
    private Long id;

    @JsonProperty("enrollment_date")
    private LocalDate enrollmentDate;

    @JsonProperty("enrollment_status")
    private String enrollmentStatus;

    @JsonProperty("student_id")
    private Long studentId;

    @JsonProperty("course_id")
    private Long courseId;

    @JsonProperty("created_at")
    private LocalDateTime createdAt;

    @JsonProperty("updated_at")
    private LocalDateTime updatedAt;
}
