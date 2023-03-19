package com.example.studentsdemo.repository;

import com.example.studentsdemo.model.Student;

import java.util.List;
import java.util.Optional;

public interface StudentsRepository {
    void save (Student student);
   Optional <Student> findByEmail(String email);

   List <Student> searchByEmail (String email);

   List <Student> findAll ();
}
