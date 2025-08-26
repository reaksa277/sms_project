package com.example.sms_project.controller;

import com.example.sms_project.dto.student.StudentDto;
import com.example.sms_project.dto.student.UpdateStudentDto;
import com.example.sms_project.model.BaseResponseModel;
import com.example.sms_project.model.BaseResponseWithDataModel;
import com.example.sms_project.service.StudentService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Positive;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/students")
public class StudentController {
    @Autowired
    private StudentService studentService;

    @GetMapping
    public ResponseEntity<BaseResponseWithDataModel> listAllStudents(){
        return studentService.listAllStudents();
    }

    @GetMapping("/{student_id}")
    public ResponseEntity<BaseResponseWithDataModel> getStudentById(@PathVariable("student_id")
                                                                        @Positive(message = "Student Id must be positive")
                                                                        Long student_id){
        return studentService.getAllStudents(student_id);
    }

    @PostMapping
    public ResponseEntity<BaseResponseModel> createStudent (@Valid @RequestBody StudentDto payload){
        return studentService.createStudent(payload);
    }

    @PutMapping
    public ResponseEntity<BaseResponseModel> updateStudent (@PathVariable("student_id") Long id,
                                                            @Valid @RequestBody UpdateStudentDto payload){
        return studentService.updateStudent(payload,id);
    }
}
