package com.example.studentsdemo.service;

import com.example.studentsdemo.dto.StudentDto;
import com.example.studentsdemo.repository.StudentsRepository;

import java.util.List;

import static com.example.studentsdemo.dto.StudentDto.from;

public class StudentServiceImpl implements StudentService{
    private final StudentsRepository studentsRepository;

    public StudentServiceImpl(StudentsRepository studentsRepository) {
        this.studentsRepository = studentsRepository;
    }

    @Override
    public List<StudentDto> getAll() {
        return from(studentsRepository.findAll());
    }

    @Override
    public List<StudentDto> searchByEmail(String email) {
        return from(studentsRepository.searchByEmail(email));
    }
}
