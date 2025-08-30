package com.example.sms_project.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;



@Entity
@Table(name = "teachers")

@Data
public class Teacher {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String first_name;
    private String last_name;
    private String email;
    private String phone;
    private String department;
    private String hire_date;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    @OneToMany(mappedBy = "courses", fetch = FetchType.LAZY,  cascade = CascadeType.ALL)
    private List<Courses> courses;
}

