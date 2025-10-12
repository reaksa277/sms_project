package com.example.sms_project.mapper;

import com.example.sms_project.dto.parent.ParentDto;
import com.example.sms_project.dto.parent.ParentResponseDto;
import com.example.sms_project.dto.parent.UpdateParentDto;
import com.example.sms_project.entity.Parent;
import com.example.sms_project.entity.Student;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class ParentMapper {
    public Parent toEntity(ParentDto dto) {
        Parent entity = new Parent();

        entity.setEmail(dto.getEmail());
        entity.setParentName(dto.getParentName());
        entity.setPhoneNumber(dto.getPhoneNumber());
        entity.setRelationship(dto.getRelationship());

        return entity;
    }

    public ParentResponseDto toResponseDto(Parent entity) {
        ParentResponseDto dto = new ParentResponseDto();

        dto.setParentId(entity.getId());
        dto.setEmail(entity.getEmail());
        dto.setParentName(entity.getParentName());
        dto.setRelationship(entity.getRelationship());
        dto.setPhoneNumber(entity.getPhoneNumber());
        dto.setCreatedAt(entity.getCreatedAt());
        dto.setUpdatedAt(entity.getUpdatedAt());

        // Map student information
        if (entity.getStudent() != null) {
            Student student = entity.getStudent();
            StudentNestedDto studentDto = new StudentNestedDto();
            studentDto.setStudentId(student.getId());
            studentDto.setStudentName(student.getStudentName());
            studentDto.setEmail(student.getEmail());
            dto.setStudent(studentDto);
        }

        return dto;
    }

    public List<ParentResponseDto> toResponseDtoList(List<Parent> entities) {
        if (entities == null) {
            return new ArrayList<>();
        }

        return entities.stream()
                .map(this::toResponseDto)
                .toList();
    }

    public void updateParentFromDto(UpdateParentDto dto, Parent entity) {
        if ((entity == null) || (dto == null)) {
            return;
        }
        entity.setParentName(dto.getParentName());
        entity.setEmail(dto.getEmail());
        entity.setPhoneNumber(dto.getPhoneNumber());
        entity.setRelationship(dto.getRelationship());
    }
}