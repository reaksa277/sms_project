package com.example.sms_project.dto;

import lombok.Data;

import java.util.Date;

@Data
public class StudentDto {

    private String studentName;
    private String email;
    private String phoneNumber;
    private Date dateOfBirth;
    private String address;

}
