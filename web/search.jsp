<%-- 
    Document   : search
    Created on : Jan 23, 2021, 3:16:59 PM
    Author     : Admin
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <link href="<c:url value="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" />" rel="stylesheet">
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" crossorigin="anonymous">
        <script src="https://code.jquery.com/jquery-3.2.1.slim.min.js" crossorigin="anonymous"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js" crossorigin="anonymous"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js" crossorigin="anonymous"></script>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <style>
            <%@include file="css/mystyle.css"%>
        </style>
        <title>Search Page</title>
    </head>
    <body class="text-center">
        <div class="cover-container p-3 mx-auto">
            <section class="jumbotron" style="background: #e8e9f2">
                <h1>SEARCH QUIZ</h1>
                <c:set var="user" value="${sessionScope.ACCOUNT_DTO}"/>
                <c:if test="${not empty user}">
                    <h3><font color="green">Welcome, ${user.name}</font></h3>
                    <h5><a href="logout">Logout</a></h5>
                </c:if>
                <div class="row">

                    <form action="search" method="POST" class="col-md-6 mx-auto">
                        <div class="row">
                            <div class="col-md-6">
                                <p><strong>Question content: </strong></p>
                                <input type="text" name="txtName" value="${param.txtName}" class="form-control"/>
                                <c:set var="searchValue" value="${param.txtName}"/>
                            </div>
                            <div class="col-md-3">
                                <p><strong> Status: </strong></p>
                                <c:set var="selectedStatus" value="${requestScope.SELECTED_STATUS}"/>
                                <select name="cbStatus" class="form-control">
                                    <option value="true" <c:if test="${selectedStatus}">
                                            selected="true"</c:if>>
                                            Activate
                                        </option>
                                        <option value="false"<c:if test="${not selectedStatus}">
                                            selected="true"</c:if>>
                                            Deactivate
                                        </option>
                                    </select>
                                <c:set var="searchStatus" value="${param.cbStatus}"/>
                                <c:set var="sList" value="${requestScope.SUBJECT_LIST}"/>
                                <c:set var="selectedSub" value="${requestScope.SELECTED_SUBJECT}"/>
                            </div>
                            <div class="col-md-3">
                                <p><strong> Subject name: </strong></p>
                                <select name="cbSubject" class="form-control">
                                    <option value="" <c:if test="${selectedSub == ''}">
                                            selected="true"</c:if>>
                                            ---------   
                                        </option>
                                    <c:forEach var="sDTO" items="${sList}">
                                        <option value="${sDTO.subjectId}" <c:if test="${selectedSub == sDTO.subjectId}">
                                                selected="true"</c:if>>
                                            ${sDTO.subjectName}
                                        </option>
                                    </c:forEach>
                                </select>
                            </div>
                            <c:set var="searchSubject" value="${param.cbSubject}"/>
                            <div class="col-md-12">
                                <input type="submit" value="Search" class="btn btn1 mx-auto"/>   
                            </div>
                            <c:url var="createUrl" value="create-question">
                                <c:param name="txtName" value="${searchValue}"/>
                                <c:param name="cbStatus" value="${searchStatus}"/>
                                <c:param name="cbSubject" value="${searchSubject}"/>
                                <c:param name="txtPage" value="${requestScope.PAGE_NUM}"/>
                            </c:url>
                            <a href="${createUrl}" class="btn btn1">Create question</a>
                        </div>
                        <c:if test="${not empty requestScope.TEST_ERR}">
                            <font color="red">${requestScope.TEST_ERR}</font>
                        </c:if>
                    </form>
                </div>
            </section>
            <div class="row">
                <c:set var="qList" value="${requestScope.QUESTION_LIST}"/>
                <c:if test="${selectedSub!=''}">  
                    <c:if test="${not empty qList}">

                        <div class="col-md-6 mx-auto">
                            <br/>

                            <c:forEach var="q" items="${qList}" varStatus="counter">
                                <c:set var="page" value="${(requestScope.PAGE_NUM - 1) * 20}"/>
                                <p><strong>Question ${counter.count + page}:</strong> ${q.question_contect}</p> 
                                <c:set var="aList" value="${q.answers}"/>
                                <c:forEach var="a" items="${aList}">
                                    <p style="padding-left: 1.8em">  ${a.answer_content}</p> 
                                    <c:if test="${a.status}">
                                        <c:set var="correct" value="${a.answer_content}"/>
                                    </c:if>
                                    <c:if test="${not a.status}">
                                        <c:set var="inCorrect" value="${a.answer_content}"/>
                                    </c:if>
                                </c:forEach>
                                <strong><p style="padding-left: 1.8em"> Correct answer: ${correct}</p> </strong>

                                <!--form delete-->
                                <form action="delete-question" method="POST">
                                    <input type="hidden" value="${searchValue}" name="txtName" />
                                    <input type="hidden" value="${q.questionId}" name="txtQuestinId" />
                                    <input type="hidden" value="${searchStatus}" name="cbStatus" />
                                    <input type="hidden" value="${searchSubject}" name="cbSubject" />
                                    <input type="hidden" name="txtPage" value="${requestScope.PAGE_NUM}" />
                                    <c:if test="${q.status}">
                                        <input type="button" value="Delete" class="btn btn1" data-toggle="modal" data-target="#exampleModalLong"/>
                                    </c:if>
                                    <div class="modal fade" id="exampleModalLong" tabindex="-1" role="dialog" aria-labelledby="exampleModalLongTitle" aria-hidden="true">
                                        <div class="modal-dialog" role="document">
                                            <div class="modal-content">
                                                <div class="modal-header">
                                                    <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                                                        <span aria-hidden="true">&times;</span>
                                                    </button>
                                                </div>
                                                <div class="modal-body">
                                                    Do you want to delete?
                                                </div>
                                                <div class="modal-footer">
                                                    <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
                                                    <input type="submit" value="Delete" name="btAction" class="btn btn1"/>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </form>

                                <div class="row">
                                    <div class="col-md-6 mx-auto">
                                        <c:url var="updateQuestion" value="update-question">
                                            <c:param name="txtQuestionId" value="${q.questionId}"/>
                                            <c:param value="${searchValue}" name="txtName" />
                                            <c:param name="cbStatus" value="${searchStatus}"/>
                                            <c:param name="cbSubject" value="${searchSubject}"/>
                                            <c:param name="txtPage" value="${requestScope.PAGE_NUM}"/>
                                        </c:url>
                                        <a href="${updateQuestion}" class="btn btn1">Update</a>
                                    </div>
                                </div>
                            </c:forEach>
                        </div>     

                        <div class="col-md-12">
                            <form action="search" method="POST">
                                <c:set var="countPage" value="${requestScope.PAGE_COUNT}"/>
                                <div>
                                    <input type="hidden" value="${searchValue}" name="txtName" />
                                    <input type="hidden" value="${searchStatus}" name="cbStatus" />
                                    <input type="hidden" value="${searchSubject}" name="cbSubject" />
                                    <c:forEach var="num" begin="${1}" end="${countPage}">
                                        <input type="submit" value="${num}" class="btn btn2" name="btnPage"/>
                                    </c:forEach>
                                </div>
                            </form>    
                        </div>  
                    </c:if>
                    <div class="col-md-12">
                        <c:if test="${empty qList}">
                            <h5 style="padding: 50px">Empty List</h5>
                        </c:if>
                    </div>
                </c:if>
                <div class="col-md-12">
                    <c:if test="${selectedSub==''}">
                        <h5 style="padding: 50px">Please choose subject name</h5>
                    </c:if>
                </div>
            </div>
        </div>


    </body>
</html>
