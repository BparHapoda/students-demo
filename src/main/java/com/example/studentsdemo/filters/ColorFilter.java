package com.example.studentsdemo.filters;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebFilter("/*")
public class ColorFilter implements Filter {
public static String COLOR_COOKIE_NAME="pageColor";
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
     System.out.println("Filter initilization");
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest httpServletRequest= (HttpServletRequest) servletRequest;
        HttpServletResponse httpServletResponse= (HttpServletResponse) servletResponse;

        Cookie [] cookies= httpServletRequest.getCookies();
        if(cookies!=null){
            for(Cookie cookie:cookies){
                if (cookie.getName().equals("COLOR_COOKIE_NAME")){
                    httpServletRequest.setAttribute("color",cookie.getValue());
                }
            }
        }
        filterChain.doFilter(servletRequest,servletResponse);
    }

    @Override
    public void destroy() {
        System.out.println("Filter destruction");
    }
}
