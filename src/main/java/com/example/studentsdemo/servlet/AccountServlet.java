package com.example.studentsdemo.servlet;


import com.example.studentsdemo.filters.ColorFilter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/account")
public class AccountServlet extends HttpServlet {
    private final int COLOR_COOKIE_MAX_AGE=365*24*60*60;


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String pageColor=req.getParameter("color");
        if(pageColor!=null){
            req.setAttribute("color",pageColor);
            Cookie cookie=new Cookie(ColorFilter.COLOR_COOKIE_NAME,pageColor);
            cookie.setMaxAge(COLOR_COOKIE_MAX_AGE);
            resp.addCookie(cookie);
        }

        req.getRequestDispatcher("/jsp/account.jsp").forward(req,resp);
    }


}
