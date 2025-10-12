package com.example.sms_project.controller;

import com.example.sms_project.dto.parent.ParentDto;
import com.example.sms_project.dto.parent.ParentResponseDto;
import com.example.sms_project.dto.parent.UpdateParentDto;
import com.example.sms_project.model.BaseResponseModel;
import com.example.sms_project.model.BaseResponseWithDataModel;
import com.example.sms_project.service.ParentService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Positive;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/parents")
public class ParentController {
    @Autowired
    private ParentService parentService;

    @GetMapping
    public ResponseEntity<BaseResponseWithDataModel> listAllParents() {
        return parentService.listAllParents();
    }

    @GetMapping("/{parent_id}")
    public ResponseEntity<BaseResponseWithDataModel> getParentById(
            @PathVariable("parent_id")
            @Positive(message = "Parent Id cannot be negative")
            Long id) {
        return parentService.getParentById(id);
    }

    @PostMapping
    public ResponseEntity<BaseResponseWithDataModel> createParent(@RequestBody @Valid ParentDto payload) {
        return parentService.createParent(payload);
    }

    @PutMapping("/{parent_id}")
    public ResponseEntity<BaseResponseModel> updateParent(
            @PathVariable("parent_id") Long id,
            @Valid @RequestBody UpdateParentDto payload) {
        return parentService.updateParent(payload, id);
    }

    @DeleteMapping("/{parent_id}")
    public ResponseEntity<BaseResponseModel> deleteParent(@PathVariable("parent_id") Long id) {
        return parentService.deleteParent(id);
    }

    @GetMapping("/search")
    public ResponseEntity<BaseResponseWithDataModel> searchParent(
            @RequestParam(required = false) String name,
            @RequestParam(required = false) Long id,
            @RequestParam(required = false) String email,
            @RequestParam(required = false) String relationship
    ) {
        List<ParentResponseDto> results = parentService.searchParent(name, id, email);

        return ResponseEntity.ok(
                new BaseResponseWithDataModel("success", "parents found", results)
        );
    }
}