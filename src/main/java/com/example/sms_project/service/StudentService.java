package com.example.sms_project.service;

import com.example.sms_project.dto.student.StudentDto;
import com.example.sms_project.dto.student.StudentResponseDto;
import com.example.sms_project.dto.student.UpdateStudentDto;
import com.example.sms_project.entity.Student;
import com.example.sms_project.exception.model.ResourceNotFoundException;
import com.example.sms_project.mapper.StudentMapper;
import com.example.sms_project.model.BaseResponseModel;
import com.example.sms_project.model.BaseResponseWithDataModel;
import com.example.sms_project.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;


import java.util.List;

@Service
public class StudentService {
    @Autowired
    private StudentMapper mapper;

    @Autowired
    private StudentRepository studentRepository;

    public ResponseEntity<BaseResponseWithDataModel> listAllStudents() {

        List<Student> students = studentRepository.findAll();

        List<StudentResponseDto> dtos = mapper.toResponseDtoList(students);

        return ResponseEntity.ok(new BaseResponseWithDataModel("success", "success",
                dtos));
    }

    public ResponseEntity<BaseResponseWithDataModel> getAllStudents(Long id) {
        Student student = studentRepository.findById(id).
                orElseThrow(()-> new ResourceNotFoundException("student not found for this id:" + id));

        StudentResponseDto dto = mapper.toResponseDto(student);

        return ResponseEntity.ok(new BaseResponseWithDataModel("success", "success", dto));
    }

    public ResponseEntity<BaseResponseModel> createStudent(StudentDto payload) {

        if (studentRepository.existsByStudentName(payload.getStudentName())) {
            throw new ResourceNotFoundException("student already exists");
        }

        if (studentRepository.existsByEmail(payload.getEmail())) {
            throw new ResourceNotFoundException("email already exists");
        }

        Student student = mapper.toEntity(payload);

        studentRepository.save(student);

        return ResponseEntity.status(HttpStatus.CREATED).
                body(new BaseResponseModel("success", "student created"));
    }

    public ResponseEntity<BaseResponseModel> updateStudent(UpdateStudentDto payload, Long id) {

        Student existing = studentRepository.findById(id).
                orElseThrow(()-> new ResourceNotFoundException("student not found for this id:" + id));

        mapper.updateStudentFromDto(payload, existing);
        studentRepository.save(existing);
        return ResponseEntity.status(HttpStatus.OK)
                .body(new BaseResponseModel("success", "student updated"));
    }


}
