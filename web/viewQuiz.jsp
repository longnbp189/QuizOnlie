<%-- 
    Document   : viewQuiz
    Created on : Feb 24, 2021, 8:21:15 PM
    Author     : Admin
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <link href="<c:url value="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" />" rel="stylesheet">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" crossorigin="anonymous">
    <script src="https://code.jquery.com/jquery-3.2.1.slim.min.js" crossorigin="anonymous"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js" crossorigin="anonymous"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js" crossorigin="anonymous"></script>
    <style>
        <%@include file="css/mystyle.css"%>
        p{
            font-size: 20px;
        }
    </style>

    <title>Review</title>
</head>
<body class="text-center">
    <div class="cover-container p-3 mx-auto">
        <section class="jumbotron" style="background: #e8e9f2">
            <div class="container">
                <h1>Review quiz</h1>
                <c:set var="user" value="${sessionScope.ACCOUNT_DTO}"/>
                <c:if test="${not empty user}">
                    <h3><font color="green">Welcome, ${user.name}</font></h3>
                    <h5><a href="logout">Logout</a></h5>
                </c:if>
            </div>
            <a href="getSubject" class="btn btn1">Home</a>
            <a href="history-quiz" class="btn btn1">Show history</a>
        </section>

        <div class="row">
            <div class="mx-auto row">
                <div class="col-md-10" style="margin-left: 75px">
                    <c:set var="q" value="${requestScope.HISTORY_QUIZ}"/>

                    <c:forEach var="v" items="${q}" varStatus="counter">
                        <br/>
                        <p><strong>Question ${counter.count}: ${v.questionContent}</strong></p>
                        <c:set var="aList" value="${v.answers}"/>
                        <c:set var="count" value="0"/>
                        <c:forEach var="a" items="${aList}">
                            <c:if test="${a.status}">
                                <c:set var="correct" value="${a.answer_content}"/>
                                    </c:if>
                                    <c:if test="${not a.status}">
                                <p style="padding-left: 1.8em">Answer ${count = count + 1}: ${a.answer_content}</p>
                            </c:if>
                        </c:forEach>
                        <p style="padding-left: 1.8em">Correct answer: ${correct}</p>
                                <c:if test="${empty v.answerContent}">
                            <p>Student answer: <strong>No answer</strong></p>
                        </c:if>
                        <c:if test="${not empty v.answerContent && v.status}">
                            <p>Student answer: <strong><font style="color: green">${v.answerContent}</font></strong></p>
                            </c:if>
                            <c:if test="${not empty v.answerContent && not v.status}">
                            <p>Student answer: <strong><font style="color: red">${v.answerContent}</font></strong></p> 
                            </c:if>
                        <br/>
                    </c:forEach>
                </div>
            </div>

        </div>
    </div>

</body>
</html>
