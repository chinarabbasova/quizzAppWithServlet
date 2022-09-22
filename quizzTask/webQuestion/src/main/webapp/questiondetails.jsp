<%@ page import="dao.inter.QuestionDaoInter" %>
<%@ page import="main.Context" %>
<%@ page import="entity.Questions" %><%--
  Created by IntelliJ IDEA.
  User: Chinar.Abbasova
  Date: 9/1/2022
  Time: 03:06 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <link rel="stylesheet" href="css/style.css">
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.0.0/dist/css/bootstrap.min.css"
          integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
    <%--    <link rel="stylesheet" href="css/bootstrap.css"/>--%>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/css/bootstrap.min.css"
          integrity="sha384-xOolHFLEh07PJGoPkLv1IbcEPTNtaed2xpHsD9ESMhqIYd0nLMwNLD69Npy4HI+N" crossorigin="anonymous">

    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
</head>
<body>
<%
    Questions ques = (Questions) request.getAttribute("ques");

%>

<div class="container">
    <div class="row">
        <div class="container h-100">
            <div class="row h-100 justify-content-center align-items-center">
                <div class="box">
                    <h1>Update Question</h1>
                    <div>
                        <form action="questiondetails" method="POST">
                            <input type="hidden" name="action" value="update"/>
                            <input type="hidden" name="id" value="<%=ques.getId()%>">
                            <label class="col-sm-4 col-form-label"> Title:</label>
                            <input type="text" name="title" value="<%=ques.getTitle()%>"/>
                            <br/>
                            <label class="col-sm-4 col-form-label"> First option :</label>
                            <input type="text" name="optionA" value="<%=ques.getOptionA()%>"/>
                            <br/>
                            <label class="col-sm-4 col-form-label"> Second option :</label>
                            <input type="text" name="optionB" value="<%=ques.getOptionB()%>"/>
                            <br/>
                            <label class="col-sm-4 col-form-label"> Third option:</label>
                            <input type="text" name="optionC" value="<%=ques.getOptionC()%>"/>
                            <br/>
                            <label class="col-sm-4 col-form-label"> Forth option :</label>
                            <input type="text" name="optionD" value="<%=ques.getOptionD()%>"/>
                            <br/>
                            <label  class="col-sm-4 col-form-label"> Right answer: </label>
                            <input type="text" name="ans" value="<%=ques.getAns()%>"/>
                            <br/>
                            <input type="submit" name="save" class="btn-primary" value="Save"/>
                        </form>

                    </div>
                </div>
            </div>
        </div>
    </div>
</div>

</body>
</html>
