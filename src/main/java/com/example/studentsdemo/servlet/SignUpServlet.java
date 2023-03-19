package com.example.studentsdemo.servlet;

import com.example.studentsdemo.dto.SignUpForm;
import com.example.studentsdemo.repository.StudentsRepository;
import com.example.studentsdemo.repository.StudentsRepositoryImpl;
import com.example.studentsdemo.service.SignUpService;
import com.example.studentsdemo.service.SignUpServiceImpl;
import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import javax.sql.DataSource;
import java.io.IOException;

@WebServlet("/signUp")
public class SignUpServlet extends HttpServlet {
    private SignUpService signUpService;

    @Override
    public void init(ServletConfig config) throws ServletException {
        ServletContext servletContext=config.getServletContext();
        DataSource dataSource= (DataSource) servletContext.getAttribute("dataSource");
        StudentsRepository studentsRepository=new StudentsRepositoryImpl(dataSource);
        this.signUpService = new SignUpServiceImpl(studentsRepository);

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        SignUpForm signUpForm = SignUpForm.builder()
                .firstName(req.getParameter("firstName"))
                .lastName(req.getParameter("lastName"))
                .email(req.getParameter("email"))
                .password(req.getParameter("password"))
                .build();
        signUpService.signUp(signUpForm);
        resp.sendRedirect("/students_demo_war_exploded/signIn");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("jsp/signUp.jsp").forward(req, resp);
    }
}
