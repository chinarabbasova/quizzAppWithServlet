package com.example.webquestion.controller;

import com.fasterxml.jackson.annotation.JsonValue;
import dao.impl.QuestionDaoImpl;
import dao.inter.QuestionDaoInter;
import entity.Questions;
import main.Context;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.simple.JSONValue;

import javax.script.Invocable;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;


@WebServlet(name = "CheckQuesController", urlPatterns = {"/checkques"})
public class CheckQuesController extends HttpServlet {
    private QuestionDaoInter quesDao = Context.instanceQuesDao();


    @Override
    protected void doPost(HttpServletRequest request,
                          HttpServletResponse response) throws ServletException, IOException {
        int right =0;
        int wrong = 0;
        PrintWriter out = response.getWriter();
        Map<Integer, Integer> RequestMap = new HashMap<>();

        QuestionDaoInter quD = new QuestionDaoImpl();
        Map<Integer, Integer> dbMap = quD.getByIdAndAns();

        String[] idFromRequest = request.getParameterValues("t[]");
        int questionId;

        for (int i = 0; i < idFromRequest.length; i++) {
            String idFromRequestStr = request.getParameter("q" + idFromRequest[i]);
            if (idFromRequestStr != null) {
                questionId = Integer.parseInt(idFromRequest[i]);
                //out.println("idFromRequestStr  " + idFromRequestStr);
                int ansFromReq = Integer.parseInt(idFromRequestStr);
                RequestMap.put(questionId, ansFromReq);
               //out.println("RequestMap" + RequestMap);

                if( ansFromReq ==  dbMap.get(questionId) ){
                    right++;
                    out.println(questionId +" - suala duz cavab verdiniz");
                }else{
                    wrong++;
                    out.println(questionId +" - suala sehv cavab verdiniz");
                }


            }
        }
        out.println("duz cavablar:" + right);
        out.println("sehv cavablar:" + wrong);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws
            ServletException, IOException {
        request.getRequestDispatcher("quiz.jsp").forward(request, response);

    }
}
