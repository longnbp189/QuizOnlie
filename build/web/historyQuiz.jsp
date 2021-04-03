<%-- 
    Document   : historyQuiz
    Created on : Feb 22, 2021, 7:26:46 AM
    Author     : Admin
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>History quiz</title>
        <link href="<c:url value="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" />" rel="stylesheet">
        <style>
            <%@include file="css/mystyle.css"%>
        </style>
    </head>
    <body class="text-center">
        <div class="cover-container p-3 mx-auto">
            <section class="jumbotron" style="background: #e8e9f2">
                <div class="container">
                    <h1>History Quiz</h1>
                    <c:set var="user" value="${sessionScope.ACCOUNT_DTO}"/>
                    <c:if test="${not empty user}">
                        <h3><font color="green">Welcome, ${user.name}</font></h3>
                        <h5><a href="logout">Logout</a></h5>
                    </c:if>
                </div>
                <a href="getSubject" class="btn btn1">Home</a>
                <div class="row">
                    <form action="history-quiz" method="POST" class="col-md-5 mx-auto">
                        <div class="row">
                            <c:set var="selectedSub" value="${requestScope.SELECTED_SUBJECT}"/>
                            <c:set var="sList" value="${requestScope.SUBJECT_LIST}"/>
                            <div class="col-md-4">
                                <p><strong>Subject name: </strong></p>
                            </div>
                            <div class="col-md-5">
                                <select name="cbSubject" class="form-control">
                                    <option value="All" >
                                        All
                                    </option>
                                    <c:forEach var="sDTO" items="${sList}">
                                        <option value="${sDTO.subjectId}" <c:if test="${selectedSub == sDTO.subjectId}">
                                                selected="true"</c:if>>
                                            ${sDTO.subjectName}
                                        </option>
                                    </c:forEach>
                                </select>
                            </div>
                            <div class="col-md-3">
                                <input type="submit" value="Search" class="btn btn1" style="margin: -9px"/>   
                            </div>
                        </div>
                    </form>
                </div>
            </section>

            <c:set var="hList" value="${requestScope.HISTORY}"/>
            <c:if test="${not empty hList}">
                <section class="py-5 container">
                    <div class="row">
                        <div class="col-md-12 mx-auto">
                            <table border="1" class="mx-auto">
                                <thead>
                                    <tr>
                                        <th>No.</th>
                                        <th>Subject name</th>
                                        <th>Submit time</th>
                                        <th>Correct answer</th>
                                        <th>Grade</th>
                                        <th>Review</th>
                                    </tr>
                                </thead>
                                <tbody>
                                    <c:forEach items="${hList}" var="hDTO" varStatus="counter">
                                        <tr>
                                            <td>${counter.count}</td>
                                            <td>${hDTO.subjectId}</td>
                                            <td>${hDTO.time}</td>
                                            <td>${hDTO.correct_answer}</td>
                                            <td>${hDTO.grade}</td>
                                            <c:url var="viewDetail" value="view-quiz-history">
                                                <c:param value="${hDTO.id}" name="txtHistoryId"/>
                                            </c:url>
                                            <td><a href="${viewDetail}">Review</a></td>
                                        </tr>
                                    </c:forEach>

                                </tbody>
                            </table>
                        </div>
                </section>
            </c:if>
            <c:if test="${empty hList}">
                <h5 style="padding: 50px">Can not found</h5>
            </c:if>
        </div>
    </div>
</body>
</html>
