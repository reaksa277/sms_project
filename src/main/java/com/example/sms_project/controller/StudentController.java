package com.example.sms_project.controller;

import com.example.sms_project.dto.student.StudentDto;
import com.example.sms_project.dto.student.StudentResponseDto;
import com.example.sms_project.dto.student.UpdateStudentDto;
import com.example.sms_project.model.BaseResponseModel;
import com.example.sms_project.model.BaseResponseWithDataModel;
import com.example.sms_project.service.StudentService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Positive;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/students")

public class StudentController {

    @Autowired
    private  StudentService studentService;

    @GetMapping
    public ResponseEntity<BaseResponseWithDataModel> listAllStudents(){
        return studentService.listAllStudents();
    }

    @GetMapping("/{student_id}")
    public ResponseEntity<BaseResponseWithDataModel> getStudentById(
            @PathVariable("student_id")
            @Positive(message = "Student Id must be positive")
            Long student_id) {
        return studentService.getStudentById(student_id);
    }

    @PostMapping
    public ResponseEntity<BaseResponseModel> createStudent(@Valid @RequestBody StudentDto payload){
        return studentService.createStudent(payload);
    }

    @PutMapping("/{student_id}")
    public ResponseEntity<BaseResponseModel> updateStudent(
            @PathVariable("student_id") Long id,
            @Valid @RequestBody UpdateStudentDto payload) {
        return studentService.updateStudent(payload, id);
    }

    @DeleteMapping("/{student_id}")
    public ResponseEntity<BaseResponseModel> deleteStudent(@PathVariable("student_id") Long id){
        return studentService.deleteStudent(id);
    }

    @GetMapping("/search")
    public ResponseEntity<BaseResponseWithDataModel> searchStudents(
            @RequestParam(required = false) String name,
            @RequestParam(required = false) Long id,
            @RequestParam(required = false) String email) {

        List<StudentResponseDto> results = studentService.searchStudent(name, id, email);

        return ResponseEntity.ok(
                new BaseResponseWithDataModel("success", "students found", results)
        );
    }
}
