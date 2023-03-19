package com.example.studentsdemo.servlet;

import com.example.studentsdemo.dto.SignInForm;
import com.example.studentsdemo.repository.StudentsRepository;
import com.example.studentsdemo.repository.StudentsRepositoryImpl;
import com.example.studentsdemo.service.SignInService;
import com.example.studentsdemo.service.SignInServiceImpl;
import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import javax.sql.DataSource;
import java.io.IOException;

@WebServlet("/signIn")
public class SignInServlet extends HttpServlet {
private SignInService signInService;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
req.getRequestDispatcher("/jsp/signIn.jsp").forward(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
SignInForm signInForm= SignInForm.builder()
        .email(req.getParameter("email"))
        .password(req.getParameter("password"))
        .build();

if(signInService.doAuthenticate(signInForm)){
  //  HttpSession httpSession= req.getSession(true);
    resp.sendRedirect("/students_demo_war_exploded/account");
}
else {resp.sendRedirect("/students_demo_war_exploded/signIn/?error");}
    }

    @Override
    public void init(ServletConfig config) throws ServletException {
        ServletContext servletContext=config.getServletContext();
        DataSource dataSource= (DataSource) servletContext.getAttribute("dataSource");
        StudentsRepository studentsRepository=new StudentsRepositoryImpl(dataSource);
        this.signInService=new SignInServiceImpl(studentsRepository);
    }
}
