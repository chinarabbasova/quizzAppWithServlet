package com.example.webquestion.filter;

import javax.servlet.*;
import javax.servlet.annotation.*;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebFilter(filterName = "JSPFileFilter",urlPatterns = {"*.jsp"})
public class JspFilter implements Filter {
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain){
        HttpServletResponse res = (HttpServletResponse) response;
        try{
            res.sendRedirect("error?msg=not found");
        } catch (Exception e){
            e.printStackTrace();
        }
    }

}
