package com.example.sms_project.service;

import com.example.sms_project.dto.enrollment.EnrollmentDto;
import com.example.sms_project.dto.enrollment.EnrollmentResponseDto;
import com.example.sms_project.entity.Courses;
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

import java.util.List;

@Service
public class EnrollmentService {
    @Autowired
    private EnrollmentRepository enrollmentRepository;

    @Autowired
    private StudentRepository  studentRepository;

    @Autowired
    private EnrollmentMapper mapper;

    public List<EnrollmentResponseDto> listEnrollments() {
        List<Enrollment> enrollments = enrollmentRepository.findAll();

        return mapper.toListDto(enrollments);
    }

    public void createEnrollment(EnrollmentDto enrollment) {
        Student existingStudent = studentRepository.findById(enrollment.getStudentId())
                .orElseThrow(() -> new ResourceNotFoundException("Student not found with id: " + enrollment.getStudentId()));

//        Courses existingCourse =

        Enrollment enrollmentEntity = mapper.toEntity(enrollment, existingStudent);

        enrollmentRepository.save(enrollmentEntity);
    }

    public void updateEnrollment(Long enrollmentId, EnrollmentDto enrollment) {
        Enrollment existing = enrollmentRepository.findById(enrollmentId)
                .orElseThrow(() -> new ResourceNotFoundException("Enrollment not found with id: " + enrollmentId));

        mapper.updateEntityFromDto(existing, enrollment);
        enrollmentRepository.save(existing);
    }

    public void deleteEnrollment(Long enrollmentId) {
        if (!enrollmentRepository.existsById(enrollmentId)) {
            throw new ResourceNotFoundException("Enrollment not found with id: " + enrollmentId);
        }

        enrollmentRepository.deleteById(enrollmentId);
    }
}
