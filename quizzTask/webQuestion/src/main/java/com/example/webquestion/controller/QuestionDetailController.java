package com.example.webquestion.controller;

import dao.inter.QuestionDaoInter;
import entity.Questions;
import main.Context;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

@WebServlet(name = "QuestionDetailController", urlPatterns = {"/questiondetails"})
public class QuestionDetailController extends HttpServlet {
    private QuestionDaoInter quesDao = Context.instanceQuesDao();
    private Integer ff;

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String idStr = request.getParameter("id");
        Integer id = null;
        if (idStr != null && !(idStr.trim().isEmpty())) {
            id = Integer.parseInt(idStr);
            System.out.println("id is:"+ id);
        }
        String action = request.getParameter("action");
        if (action.equals("update")) {
            String title = request.getParameter("title");
            String optionA = request.getParameter("optionA");
            String optionB = request.getParameter("optionB");
            String optionC = request.getParameter("optionC");
            String optionD = request.getParameter("optionD");
            String ans = request.getParameter("ans");

            Questions ques = quesDao.getById(id);
            ques.setTitle(title);
            ques.setOptionA(optionA);
            ques.setOptionB(optionB);
            ques.setOptionC(optionC);
            ques.setOptionD(optionD);
            ques.setAns(Integer.valueOf(ans));
            quesDao.updateQuestion(ques);
        } else if (action.equals("delete")) {
            quesDao.removeQuestion(id);
//        } else if (action.equals("check")) {
//            String ansStr = request.getParameter("ans");
//            Integer ans = null;
//            if (ansStr != null && !(ansStr.trim().isEmpty())) {
//                ans = Integer.parseInt(ansStr);
//            }
//            Map<Integer, Integer> db = new HashMap<>();
//            db.put(id, ans);
//            Map<Integer, Integer> mm = quesDao.getByIdAndAns();
//
//            HashSet<Integer> hs = new HashSet<>(db.values());
//            HashSet<Integer> hh = new HashSet<>(mm.values());
//            if (db.keySet().equals(mm.keySet())) {
//                if (db.values().equals(mm.values())) {
//                    ff++;
//                    System.out.println(ff);
//                }
//            }


        }
        response.sendRedirect("quiz");
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws
            ServletException, IOException {
        try {
            String idStr = request.getParameter("id");
            if (idStr == null || idStr.trim().isEmpty()) {
                // request.setAttribute("msg", " specify id");
                throw new IllegalArgumentException("id is not specified");
            }
            Integer id = Integer.parseInt(idStr);
            QuestionDaoInter quesDao = Context.instanceQuesDao();
            Questions qu = quesDao.getById(id);
            if (qu == null) {
                throw new IllegalArgumentException("  There is no user with this id");
            }
            request.setAttribute("owner", true);
            request.setAttribute("ques", qu);
            request.getRequestDispatcher("questiondetails.jsp").forward(request, response);
        } catch (Exception ex) {
            ex.printStackTrace();
            response.sendRedirect("error?msg=" + ex.getMessage());
        }
    }
}
