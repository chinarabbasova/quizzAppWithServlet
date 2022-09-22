package com.example.webquestion.controller;

import at.favre.lib.crypto.bcrypt.BCrypt;
import dao.inter.QuestionDaoInter;
import entity.Questions;
import main.Context;
import net.bytebuddy.pool.TypePool;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "LoginController", urlPatterns ={ "/login"})
public class LoginController extends HttpServlet {
    private QuestionDaoInter quesDao = Context.instanceQuesDao();
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("login.jsp").forward(request,response);
    }

    private static BCrypt.Verifyer verifyer = BCrypt.verifyer();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try{
            String email = request.getParameter("email");
            String password = request.getParameter("password");
           Questions ques = quesDao.findByEmail(email);
            if (ques == null) {
                throw new IllegalArgumentException("user doesn`t exist !");
            }
            BCrypt.Result rs = verifyer.verify(password.toCharArray(),ques.getPassword().toCharArray());
           if(!rs.verified){
               throw new IllegalArgumentException("password is incorrect!");
           }
            request.getSession().setAttribute("loggedInUser",ques);
       response.sendRedirect("quiz");
        } catch (Exception ex){
            response.sendRedirect("error?msg=" + ex.getMessage());
        }
    }
}
