<%@ page import="dao.inter.QuestionDaoInter" %>
<%@ page import="main.Context" %>
<%@ page import="entity.Questions" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.Map" %>
<%@ page import="java.util.HashMap" %>
<%@ page import="java.util.stream.Collectors" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>JSP - Hello World</title>
    <link rel="stylesheet" href="css/style.css">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js"
            integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM"
            crossorigin="anonymous"></script>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.0.0/dist/css/bootstrap.min.css"
          integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
    <%--    <link rel="stylesheet" href="css/bootstrap.css"/>--%>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/css/bootstrap.min.css"
          integrity="sha384-xOolHFLEh07PJGoPkLv1IbcEPTNtaed2xpHsD9ESMhqIYd0nLMwNLD69Npy4HI+N" crossorigin="anonymous">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
    <%--    <link rel="stylesheet" href="path/to/font-awesome/css/font-awesome.min.css">--%>
    <%--    <script type="text/javascript" src="assets/js/users.js"></script>--%>
    <script src="https://code.jquery.com/jquery-3.2.1.slim.min.js"
            integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN"
            crossorigin="anonymous"></script>
    <script src="https://cdn.jsdelivr.net/npm/popper.js@1.12.9/dist/umd/popper.min.js"
            integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q"
            crossorigin="anonymous"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.0.0/dist/js/bootstrap.min.js"
            integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl"
            crossorigin="anonymous"></script>
    <script type="text/javascript" src="js/question.js"></script>
</head>
<body>
<%
    Questions ques = (Questions) session.getAttribute("loggedInUser");

%>
<%="Welcome"+ ques.getId()%>
<%
    QuestionDaoInter quesDao = Context.instanceQuesDao();
    String idStr = request.getParameter("id");
    Integer id = null;
    if (idStr != null && !(idStr.trim().isEmpty())) {
        id = Integer.parseInt(idStr);
    }
    String title = request.getParameter("title");
    String optionA = request.getParameter("optionA");
    String optionB = request.getParameter("optionB");
    String optionC = request.getParameter("optionC");
    String optionD = request.getParameter("optionD");
    String ansStr = request.getParameter("ans");
    Integer ans = null;
    if (ansStr != null && !(ansStr.trim().isEmpty())) {
        ans = Integer.parseInt(ansStr);
    }
    String chooseStr = request.getParameter("choose");
    Integer choose = null;
    if (chooseStr != null && !(chooseStr.trim().isEmpty())) {
        choose = Integer.parseInt(chooseStr);
    }
    List<Questions> list = quesDao.getAllQuestions(title, optionA, optionB, optionC, optionD, ans, choose);

Map <Integer,Integer> map = quesDao.getByIdAndAns();
%>

<form method="POST" action="checkques">
    <div class="question-wrap">
        <%
            int a = 1;
            for (Questions q : list) {
        %>
<%--       <input type="hidden" name="test" value="test"  >--%>
        <div class="question-item">
            <div class="question-title"><%=a%>) <%=q.getTitle()%></div>
            <div class="question-option-wrap">
                <input type="hidden" name="t[]" value="<%=q.getId()%>">
                <label class="question-check"><input type="checkbox" name="q<%=q.getId()%>" value="1"><%=q.getOptionA()%></label>
                <label class="question-check"><input type="checkbox" name="q<%=q.getId()%>" value="2"><%=q.getOptionB()%></label>
                <label class="question-check"><input type="checkbox" name="q<%=q.getId()%>" value="3"><%=q.getOptionC()%></label>
                <label class="question-check"><input type="checkbox" name="q<%=q.getId()%>" value="4"><%=q.getOptionD()%></label>
            </div>
        </div>
        <%
                a++;
            }
        %>
    </div>
    <button >Show result</button>
</form>

</body>
</html>