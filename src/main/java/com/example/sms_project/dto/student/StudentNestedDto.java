package com.example.sms_project.dto.student;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class StudentNestedDto {
    @JsonProperty("student_id")
    private Long studentId;

    @JsonProperty("student_name")
    private String studentName;

    private String email;
}
