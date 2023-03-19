package com.example.studentsdemo.servlet;

import com.example.studentsdemo.dto.SignUpForm;
import com.example.studentsdemo.dto.StudentDto;
import com.example.studentsdemo.model.Student;
import com.example.studentsdemo.repository.StudentsRepository;
import com.example.studentsdemo.repository.StudentsRepositoryImpl;
import com.example.studentsdemo.service.SignUpService;
import com.example.studentsdemo.service.SignUpServiceImpl;
import com.example.studentsdemo.service.StudentService;
import com.example.studentsdemo.service.StudentServiceImpl;
import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import javax.sql.DataSource;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/students")
public class StudentsServlet extends HttpServlet {
    private StudentService studentService;

    @Override
    public void init(ServletConfig config) throws ServletException {
        ServletContext servletContext=config.getServletContext();
        DataSource dataSource= (DataSource) servletContext.getAttribute("dataSource");
        StudentsRepository studentsRepository=new StudentsRepositoryImpl(dataSource);
        this.studentService = new StudentServiceImpl((studentsRepository));

    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<StudentDto> students=studentService.getAll();
        req.setAttribute("students",students);
        req.getRequestDispatcher("jsp/students.jsp").forward(req, resp);
    }
}
