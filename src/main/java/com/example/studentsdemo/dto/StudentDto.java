package com.example.studentsdemo.dto;

import com.example.studentsdemo.model.Student;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class StudentDto {
    private Long id;
    private String firstName;
    private String lastName;
    private String email;


    public static StudentDto from (Student student){
        return StudentDto.builder().
                id(student.getId()).
                firstName(student.getFirstName()).
                lastName(student.getLastName()).
                email(student.getEmail()).
                build();
    }

    public static List <StudentDto> from (List<Student> students){
      return students.stream().map(StudentDto::from).collect(Collectors.toList());
    }
}
