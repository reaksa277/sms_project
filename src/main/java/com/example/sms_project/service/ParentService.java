package com.example.sms_project.service;

import com.example.sms_project.dto.parent.ParentDto;
import com.example.sms_project.dto.parent.ParentResponseDto;
import com.example.sms_project.dto.parent.UpdateParentDto;
import com.example.sms_project.entity.Parent;
import com.example.sms_project.entity.Student;
import com.example.sms_project.exception.model.DuplicateResourceException;
import com.example.sms_project.exception.model.ResourceNotFoundException;
import com.example.sms_project.mapper.ParentMapper;
import com.example.sms_project.model.BaseResponseModel;
import com.example.sms_project.model.BaseResponseWithDataModel;
import com.example.sms_project.repository.ParentRepository;
import com.example.sms_project.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ParentService {
    @Autowired
    private ParentMapper mapper;

    @Autowired
    private ParentRepository parentRepository;

    @Autowired
    private StudentRepository studentRepository;

    public ParentService(ParentMapper mapper, ParentRepository parentRepository) {
        this.mapper = mapper;
        this.parentRepository = parentRepository;
    }

    public ResponseEntity<BaseResponseWithDataModel> listAllParents() {
        List<Parent> parents = parentRepository.findAll();
        List<ParentResponseDto> dtos = mapper.toResponseDtoList(parents);

        return ResponseEntity.ok(new BaseResponseWithDataModel("success", "success", dtos));
    }

    public ResponseEntity<BaseResponseWithDataModel> getParentById(Long id) {
        Parent parent = parentRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Parent not found for this id: " + id));
        ParentResponseDto dto = mapper.toResponseDto(parent);

        return ResponseEntity.ok(new BaseResponseWithDataModel("success", "success", dto));
    }

    public ResponseEntity<BaseResponseWithDataModel> createParent(ParentDto payload) {
        if (parentRepository.existsByEmail(payload.getEmail())) {
            throw new DuplicateResourceException("Email already exists");
        }

        Parent parent = mapper.toEntity(payload);

        Student student = studentRepository.findById(payload.getStudentId())
                .orElseThrow(() -> new ResourceNotFoundException("Student not found for this id: " + payload.getStudentId()));
        parent.setStudent(student);

        Parent savedParent = parentRepository.save(parent);


        ParentResponseDto responseDto = mapper.toResponseDto(savedParent);

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(new BaseResponseWithDataModel("success", "Parent created", responseDto));
    }

    public ResponseEntity<BaseResponseModel> updateParent(UpdateParentDto payload, Long id) {
        Parent existing = parentRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Parent not found for this id: " + id));

        mapper.updateParentFromDto(payload, existing);
        parentRepository.save(existing);

        return ResponseEntity.ok(new BaseResponseModel("success", "Parent updated"));
    }

    public ResponseEntity<BaseResponseModel> deleteParent(Long id) {
        if (!parentRepository.existsById(id)) {
            throw new ResourceNotFoundException("Parent not found for this id: " + id);
        }
        parentRepository.deleteById(id);
        return ResponseEntity.ok(new BaseResponseModel("success", "Parent deleted"));
    }

    public List<ParentResponseDto> searchParent(String name, Long id, String email) {
        List<Parent> parents = parentRepository.searchParents(name, email, null, id);
        return mapper.toResponseDtoList(parents);
    }
}