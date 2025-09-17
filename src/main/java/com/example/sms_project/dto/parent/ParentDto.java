package com.example.sms_project.dto.parent;


import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ParentDto {
   @JsonProperty("parent_name")
   @NotBlank(message = "Parent name is mandatory")
    private String parentName;

   @JsonProperty("phone_number")
   @NotBlank(message = "Phone number is needed")
    private String phoneNumber;

   @NotBlank(message = "Email is required")
   @Email(message = "Email is needed")
    private String email;
    private String relationship;
}
