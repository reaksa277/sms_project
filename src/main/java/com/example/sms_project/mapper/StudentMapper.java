package com.example.sms_project.mapper;

import com.example.sms_project.dto.student.StudentDto;
import com.example.sms_project.dto.student.StudentResponseDto;
import com.example.sms_project.dto.student.UpdateStudentDto;
import com.example.sms_project.entity.Student;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class StudentMapper {
    public Student toEntity(StudentDto dto){
        Student entity = new Student();

        entity.setStudentName(dto.getStudentName());
        entity.setEmail(dto.getEmail());
        entity.setAddress(dto.getAddress());
        entity.setPhoneNumber(dto.getPhoneNumber());
        entity.setDateOfBirth(dto.getDateOfBirth());

        return entity;
    }
    public StudentResponseDto toResponseDto(Student entity){
        StudentResponseDto dto = new StudentResponseDto();

        dto.setStudentId(entity.getId());
        dto.setStudentName(entity.getStudentName());
        dto.setEmail(entity.getEmail());
        dto.setPhoneNumber(entity.getPhoneNumber());
        dto.setDateOfBirth(entity.getDateOfBirth());
        dto.setAddress(entity.getAddress());
        dto.setCreatedAt(entity.getCreatedAt());
        dto.setUpdatedAt(entity.getUpdatedAt());

        return dto;
    }
    public List<StudentResponseDto> toResponseDtoList(List<Student> entities){
        if (entities == null) {
            return new ArrayList<>();
        }

        return entities.stream()
                .map(this::toResponseDto)
                .toList();
    }

    public void updateStudentFromDto(UpdateStudentDto dto, Student entity){
        if ((entity == null) || (dto == null)){
            return;
        }
        entity.setStudentName(dto.getStudentName());
        entity.setEmail(dto.getEmail());
        entity.setAddress(dto.getAddress());
        entity.setPhoneNumber(dto.getPhoneNumber());
    }
}
