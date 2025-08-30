package com.example.sms_project.dto.student;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class StudentDto {

    @NotBlank(message = "Student name is mandatory")
    @JsonProperty("name")
    private String studentName;

    @Email(message = "Email is needed")
    @NotBlank(message = "Email is required")
    private String email;

    @NotBlank(message = "Phone number is needed")
    @JsonProperty("phone_number")
    private String phoneNumber;

    @NotNull(message = "Date of birth is essential")
    @JsonFormat(pattern = "yyyy-MM-dd")
    @JsonProperty("date_of_birth")
    private Date dateOfBirth;

    private String address;
}