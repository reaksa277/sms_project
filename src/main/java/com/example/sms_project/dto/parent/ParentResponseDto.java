package com.example.sms_project.dto.parent;

import com.example.sms_project.dto.student.StudentNestedDto;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ParentResponseDto {
    @JsonProperty("parent_id")
    private Long parentId;

    @JsonProperty("parent_name")
    private String parentName;

    @JsonProperty("phone_number")
    private String phoneNumber;

    private String email;

    private String relationship;


    private StudentNestedDto student;

    @JsonProperty("created_at")
    private LocalDateTime createdAt;

    @JsonProperty("updated_at")
    private LocalDateTime updatedAt;
}