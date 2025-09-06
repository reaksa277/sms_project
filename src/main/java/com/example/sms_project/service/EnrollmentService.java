package com.example.sms_project.service;

import com.example.sms_project.dto.enrollment.EnrollmentDto;
import com.example.sms_project.entity.Enrollment;
import com.example.sms_project.entity.Student;
import com.example.sms_project.exception.model.ResourceNotFoundException;
import com.example.sms_project.mapper.EnrollmentMapper;
import com.example.sms_project.model.BaseResponseModel;
import com.example.sms_project.repository.EnrollmentRepository;
import com.example.sms_project.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

@Service
public class EnrollmentService {
    @Autowired
    private EnrollmentRepository enrollmentRepository;

    @Autowired
    private StudentRepository  studentRepository;

    @Autowired
    private EnrollmentMapper mapper;

    public ResponseEntity<BaseResponseModel> createEnrollment(@RequestBody EnrollmentDto enrollment) {
        Student existingStudent = studentRepository.findById(enrollment.getStudentId())
                .orElseThrow(() -> new ResourceNotFoundException("Student not found with id: " + enrollment.getStudentId()));

        Enrollment enrollmentEntity = mapper.toEntity(enrollment, existingStudent);

        enrollmentRepository.save(enrollmentEntity);

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(new BaseResponseModel("success", "enrollment created"));
    }
}
