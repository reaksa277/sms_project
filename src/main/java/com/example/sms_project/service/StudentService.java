package com.example.sms_project.service;

import com.example.sms_project.dto.student.StudentDto;
import com.example.sms_project.dto.student.StudentResponseDto;
import com.example.sms_project.dto.student.UpdateStudentDto;
import com.example.sms_project.entity.Student;
import com.example.sms_project.exception.model.DuplicateResourceException;
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
    private  StudentMapper mapper;

    @Autowired
    private  StudentRepository studentRepository;

    public StudentService(StudentMapper mapper, StudentRepository studentRepository) {
        this.mapper = mapper;
        this.studentRepository = studentRepository;
    }

    public ResponseEntity<BaseResponseWithDataModel> listAllStudents() {
        List<Student> students = studentRepository.findAll();
        List<StudentResponseDto> dtos = mapper.toResponseDtoList(students);
        return ResponseEntity.ok(new BaseResponseWithDataModel("success", "success", dtos));
    }

    public ResponseEntity<BaseResponseWithDataModel> getStudentById(Long id) {
        Student student = studentRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Student not found for this id: " + id));
        StudentResponseDto dto = mapper.toResponseDto(student);
        return ResponseEntity.ok(new BaseResponseWithDataModel("success", "success", dto));
    }

    public ResponseEntity<BaseResponseModel> createStudent(StudentDto payload) {
        if (studentRepository.existsByStudentName(payload.getStudentName())) {
            throw new DuplicateResourceException("Student name already exists");
        }

        if (studentRepository.existsByEmail(payload.getEmail())) {
            throw new DuplicateResourceException("Email already exists");
        }

        Student student = mapper.toEntity(payload);
        studentRepository.save(student);

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(new BaseResponseModel("success", "Student created"));
    }

    public ResponseEntity<BaseResponseModel> updateStudent(UpdateStudentDto payload, Long id) {
        Student existing = studentRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Student not found for this id: " + id));

        mapper.updateStudentFromDto(payload, existing);
        studentRepository.save(existing);

        return ResponseEntity.ok(new BaseResponseModel("success", "Student updated"));
    }

    public ResponseEntity<BaseResponseModel> deleteStudent(Long id) {
        if (!studentRepository.existsById(id)) {
            throw new ResourceNotFoundException("Student not found for this id: " + id);
        }

        studentRepository.deleteById(id);
        return ResponseEntity.ok(new BaseResponseModel("success", "Student deleted"));
    }

    public List<StudentResponseDto> searchStudent(String name, Long id, String email) {
        List<Student> students = studentRepository.searchStudents(name, id, email);
        return mapper.toResponseDtoList(students);
    }
}
