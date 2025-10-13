package com.example.sms_project.controller;

import com.example.sms_project.dto.base.Response;
import com.example.sms_project.dto.enrollment.EnrollmentDto;
import com.example.sms_project.dto.enrollment.EnrollmentResponseDto;
import com.example.sms_project.model.BaseResponseModel;
import com.example.sms_project.service.EnrollmentService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping({"api/v1/enrollments"})
public class EnrollmentController {
    @Autowired
    private EnrollmentService enrollmentService;

    @GetMapping
    public ResponseEntity<Response> listEnrollments() {
        List<EnrollmentResponseDto> enrollments = enrollmentService.listEnrollments();

        return ResponseEntity.status(HttpStatus.OK)
                .body(Response.success("200", "success", "successfully retrieved enrollments list", enrollments));
    }

    @PostMapping
    public ResponseEntity<Response> createEnrollment(@Valid @RequestBody EnrollmentDto payload) {
        enrollmentService.createEnrollment(payload);

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(Response.success("200", "success", "successfully created enrollment", payload));
    }

    @PutMapping("/{enrollment_id}")
    public ResponseEntity<Response> updateEnrollment(@PathVariable("enrollment_id") Long enrollmentId, @Valid @RequestBody EnrollmentDto payload) {
        enrollmentService.updateEnrollment(enrollmentId, payload);

        return ResponseEntity.status(HttpStatus.OK)
                .body(Response.success("200", "success", "successfully updated enrollment", payload));
    }

    @DeleteMapping("/{enrollment_id}")
    public ResponseEntity<Response> deleteEnrollment(@PathVariable("enrollment_id") Long enrollmentId) {
        enrollmentService.deleteEnrollment(enrollmentId);

        return ResponseEntity.status(HttpStatus.NO_CONTENT)
                .body(Response.success("200", "success", "successfully deleted enrollment with id : " + enrollmentId , null));
    }
}
