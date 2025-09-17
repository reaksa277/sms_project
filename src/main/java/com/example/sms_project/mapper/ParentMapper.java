package com.example.sms_project.mapper;

import com.example.sms_project.dto.parent.ParentDto;
import com.example.sms_project.entity.Parent;
import org.springframework.stereotype.Component;

@Component
public class ParentMapper {
    public Parent toEntity (ParentDto dto){
        Parent entity = new Parent();

        entity.setEmail(dto.getEmail());
        entity.setParentName(dto.getParentName());
        entity.setRelationship(dto.getRelationship());

        return entity;
    }

}
