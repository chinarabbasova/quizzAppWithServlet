package com.example.webquestion.controller;

import java.io.*;
import javax.servlet.ServletException;
import javax.servlet.http.*;
import javax.servlet.annotation.*;

@WebServlet(name = "QuestionController", urlPatterns ={ "/quiz"})
public class QuestionController extends HttpServlet {

@Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

    request.getRequestDispatcher("quiz.jsp").forward(request,response);
    }


}