<%-- 
    Document   : quizResult
    Created on : Feb 21, 2021, 11:42:38 PM
    Author     : Admin
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Result</title>
        <link href="<c:url value="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" />" rel="stylesheet">
        <style>
            <%@include file="css/mystyle.css"%>
        </style>
    </head>
    <body class="text-center">
        <div class="cover-container p-3 mx-auto">
            <section class="jumbotron" style="background: #e8e9f2">
                <div class="container">
                    <h1>RESULT</h1>
                    <c:set var="user" value="${sessionScope.ACCOUNT_DTO}"/>
                    <c:if test="${not empty user}">
                        <h3><font color="green">Welcome, ${user.name}</font></h3>
                        <h5><a href="logout">Logout</a></h5>
                    </c:if>
                </div>
                <a href="getSubject" class="btn btn1">Home</a>
                <a href="history-quiz" class="btn btn1">Show history</a>
            </section>

            <section class="py-5 container">
                <div class="row">
                    <div class="col-md-12 mx-auto">
                        <table border="1" class="mx-auto">
                            <thead>
                                <tr>
                                    <th>Subject name</th>
                                    <th>Submit time</th>
                                    <th>Correct answer</th>
                                    <th>Grade</th>
                                </tr>
                            </thead>
                            <tbody>
                                <tr>
                                    <td>${requestScope.SUBJECT_NAME}</td>
                                    <td>${requestScope.TIME_SUBMIT}</td>
                                    <td>${requestScope.CORRECT}/10</td>
                                    <td>${requestScope.CORRECT}</td>
                                </tr>
                            </tbody>
                        </table>
                    </div>
            </section>

        </div>
    </div>
</body>
</html>
