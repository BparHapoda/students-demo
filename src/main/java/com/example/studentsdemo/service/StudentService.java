package com.example.studentsdemo.service;

import com.example.studentsdemo.dto.StudentDto;
import com.example.studentsdemo.model.Student;

import java.util.List;

public interface StudentService {
    List<StudentDto> getAll();
    List <StudentDto> searchByEmail(String email);
}
