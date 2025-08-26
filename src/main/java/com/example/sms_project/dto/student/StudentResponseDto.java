package com.example.sms_project.dto.student;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.Date;

@Component
@Data
@JsonPropertyOrder({"student_id", "student_name", "email", "phoneNumber",
        "dateOfBirth", "address", "created_at", "updated_at"})
public class StudentResponseDto {
    @JsonProperty("student_id")
    private Long studentId;

    @JsonProperty("student_name")
    private String studentName;
    private String email;
    private String phoneNumber;
    private Date dateOfBirth;
    private String address;

    @JsonProperty("created_at")
    private LocalDateTime createdAt;

    @JsonProperty("updated_at")
    private LocalDateTime updatedAt;

}
