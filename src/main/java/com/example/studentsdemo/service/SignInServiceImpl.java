package com.example.studentsdemo.service;

import com.example.studentsdemo.dto.SignInForm;
import com.example.studentsdemo.repository.StudentsRepository;

public class SignInServiceImpl implements SignInService{
    private final StudentsRepository studentsRepository;

    public SignInServiceImpl (StudentsRepository studentsRepository){
        this.studentsRepository = studentsRepository;
    }
    @Override
    public boolean doAuthenticate(SignInForm form) {

        return studentsRepository.findByEmail(form.getEmail()).map(student -> student.getPassword().equals(form.getPassword())).orElse(false);
    }
}