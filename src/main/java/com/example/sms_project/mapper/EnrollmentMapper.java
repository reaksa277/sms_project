package com.example.sms_project.mapper;

import com.example.sms_project.dto.enrollment.EnrollmentDto;
import com.example.sms_project.dto.enrollment.EnrollmentResponseDto;
import com.example.sms_project.entity.Courses;
import com.example.sms_project.entity.Enrollment;
import com.example.sms_project.entity.Student;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class EnrollmentMapper {

    public Enrollment toEntity(EnrollmentDto dto, Student student) {
        Enrollment entity = new Enrollment();

        entity.setEnrollmentDate(dto.getEnrollmentDate());
        entity.setStudent(student);
//        entity.setCourse(course);

        return entity;
    }

    public EnrollmentResponseDto  toDto(Enrollment  entity) {
        EnrollmentResponseDto dto = new EnrollmentResponseDto();

        dto.setId(entity.getId());
        dto.setEnrollmentDate(entity.getEnrollmentDate());
        dto.setEnrollmentStatus(entity.getEnrollmentStatus());
        dto.setStudentId(entity.getStudent().getId());
        dto.setCourseId(entity.getCourse().getId());
        dto.setCreatedAt(entity.getCreatedAt());
        dto.setUpdatedAt(entity.getUpdatedAt());

        return dto;
    }

    public List<EnrollmentResponseDto> toListDto(List<Enrollment> entities) {
        if (entities == null ||  entities.isEmpty()) {
            return new ArrayList<>();
        }
        return entities.stream()
                .map(enrollment -> this.toDto(enrollment))
                .collect(Collectors.toList());
    }

    public void updateEntityFromDto(Enrollment entity, EnrollmentDto dto) {
        if (entity == null ||  dto == null) {
            return;
        }

        entity.setEnrollmentDate(dto.getEnrollmentDate());
        entity.setEnrollmentStatus(dto.getEnrollmentStatus());
    }
}
