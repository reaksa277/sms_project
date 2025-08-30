package com.example.sms_project.dto.student;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.Date;

@Data
public class UpdateStudentDto {

    @NotBlank(message = "Student name is mandatory")
    @JsonProperty("student_name")
    private String studentName;

    @Email(message = "Email is needed")
    private String email;

    @NotBlank(message = "Relationship is needed")
    @NotNull(message = "Phone number must not be null")
    @JsonProperty("phone_number")
    private String phoneNumber;

    @NotEmpty(message = "Date of birth is needed")
    @NotNull(message = "Date of birth must not be null")
    @JsonProperty("date_of_birth")
    private Date dateOfBirth;

    @NotBlank(message = "Address is needed")
    private String address;

}
