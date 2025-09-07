package com.example.sms_project.controller;

import com.example.sms_project.dto.enrollment.EnrollmentDto;
import com.example.sms_project.model.BaseResponseModel;
import com.example.sms_project.model.BaseResponseWithDataModel;
import com.example.sms_project.service.EnrollmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping({"api/v1/enrollments"})
public class EnrollmentController {
    @Autowired
    private EnrollmentService enrollmentService;

    @GetMapping
    public ResponseEntity<BaseResponseWithDataModel> listEnrollments() {
        return enrollmentService.listEnrollments();
    }

    @PostMapping
    public ResponseEntity<BaseResponseModel> createEnrollment(@RequestBody EnrollmentDto payload) {
        return enrollmentService.createEnrollment(payload);
    }
}
