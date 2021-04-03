<%-- 
    Document   : quizHome
    Created on : Jan 23, 2021, 9:07:00 AM
    Author     : Admin
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href="<c:url value="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" />" rel="stylesheet">
        <style>
            <%@include file="css/mystyle.css"%>
        </style>
        <title>Home page</title>
    </head>
    <body class="text-center">
        <div class="cover-container p-3 mx-auto">
            <section class="jumbotron" style="background: #e8e9f2">
                <div class="container">
                    <h1>Quiz Bank</h1>
                    <c:set var="user" value="${sessionScope.ACCOUNT_DTO}"/>
                    <c:if test="${not empty user}">
                        <h3><font color="green">Welcome, ${user.name}</font></h3>
                        <h5><a href="logout">Logout</a></h5>
                    </c:if>
                        <a href="history-quiz" class="btn btn1">Show history</a>
                </div>
            </section>
            <div class="row">
                <div class="mx-auto" style="padding: 100px;">
                    <form action="doQuiz" method="POST">
                        <c:set var="sList" value="${requestScope.SUBJECT_LIST}"/>
                        <p><strong>Subject name: </strong></p>
                        <select name="cbSubject" class="form-control">
                            <option value="" >
                                --------------------
                            </option>
                            <c:forEach var="sDTO" items="${sList}">
                                <option value="${sDTO.subjectId}">
                                    ${sDTO.subjectName}
                                </option>
                            </c:forEach>
                        </select>
                        <c:set var="searchSubject" value="${param.cbSubject}"/>
                        <br/>
                        <input type="submit" value="Do Quiz" class="btn btn1"/>   
                    </form>
                    <c:if test="${not empty requestScope.SUB_ERR}">
                        ${requestScope.SUB_ERR}
                    </c:if>
                </div>
            </div>
        </div>
    </body>
</html>
