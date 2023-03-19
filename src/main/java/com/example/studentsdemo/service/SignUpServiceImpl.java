package com.example.studentsdemo.service;

import com.example.studentsdemo.dto.SignUpForm;
import com.example.studentsdemo.model.Student;
import com.example.studentsdemo.repository.StudentsRepository;

public class SignUpServiceImpl implements SignUpService {
    private static StudentsRepository studentsRepository;

    public SignUpServiceImpl(StudentsRepository studentsRepository) {
        this.studentsRepository = studentsRepository;
    }

    @Override
    public void signUp(SignUpForm signUpForm) {
        Student student = Student.builder().
                firstName(signUpForm.getFirstName()).
                lastName(signUpForm.getLastName()).
                email(signUpForm.getEmail()).
                password(signUpForm.getPassword()).build();

        studentsRepository.save(student);
    }
}
