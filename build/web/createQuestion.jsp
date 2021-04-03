<%-- 
    Document   : createQuestion
    Created on : Jan 31, 2021, 3:43:41 PM
    Author     : Admin
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href="<c:url value="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" />" rel="stylesheet">
        <title>Create Question</title>
        <style>
            <%@include file="css/mystyle.css"%>
            .abs{
                position: absolute;
                left: 30px;
            }
        </style>
    </head>
    <body class="text-center">
        <div class="cover-container p-3 mx-auto">
            <section class="jumbotron" style="background: #e8e9f2">
                <h1>CREATE QUESTION</h1>
                <c:set var="user" value="${sessionScope.ACCOUNT_DTO}"/>
                <c:if test="${not empty user}">
                    <h3><font color="green">Welcome, ${user.name}</font></h3>
                    <h5><a href="logout">Logout</a></h5>
                </c:if>
            </section>
        </div>
        <div class="row">
            <div class="col-md-6 mx-auto">
                <c:set var="qErr" value="${requestScope.QERR}"/>
                <c:set var="aErr" value="${requestScope.AERR}"/>
                <form action="create-question" >
                    <p><strong>Question content:</strong></p> <textarea name="txtQuestionContent" class="form-control">${param.txtQuestionContent}</textarea>
                    <c:if test="${not empty qErr.questionContentLengthErr}">
                        <font color="red" class="asb">
                        ${qErr.questionContentLengthErr}
                        </font>
                    </c:if>  
                    <br/>
                    <p><strong>Answer 1: </strong></p>
                    <input type="text" id="action" name="txtAnswer1" value="${param.txtAnswer1}" class="form-control" />
                    <c:if test="${not empty aErr.answer1ContentLengthErr}">
                        <font color="red" class="asb">
                        ${aErr.answer1ContentLengthErr}
                        </font>
                    </c:if>    
                    <br/>
                    <p><strong>Answer 2: </strong></p>
                    <input type="text" id="action" name="txtAnswer2" value="${param.txtAnswer2}" class="form-control"/>
                    <c:if test="${not empty aErr.answer2ContentLengthErr}">
                        <font color="red" class="asb">
                        ${aErr.answer2ContentLengthErr}
                        </font>
                    </c:if>
                    <br/>
                    <p><strong>Answer 3: </strong></p>
                    <input type="text" id="action" name="txtAnswer3" value="${param.txtAnswer3}" class="form-control"/>
                    <c:if test="${not empty aErr.answer3ContentLengthErr}">
                        <font color="red" class="asb">
                        ${aErr.answer3ContentLengthErr}
                        </font>
                    </c:if>
                    <br/>
                    <p><strong>Correct answer:</strong></p> 
                    <input type="text" name="txtCorrectAnswer" value="${param.txtCorrectAnswer}" class="form-control"/>
                    <c:if test="${not empty aErr.answerCorrectContentLengthErr}">
                        <font color="red" class="asb">
                        ${aErr.answerCorrectContentLengthErr}
                        </font>
                    </c:if>
                    <br/>
                    <p><strong>Subject:</strong></p> 
                    <c:set var="sList" value="${requestScope.SUBJECT_LIST}"/>
                    <select name="cbSubject" class="form-control">
                        <c:forEach var="sDTO" items="${sList}">
                            <option value="${sDTO.subjectId}" <c:if test="${q.subjectId == sDTO.subjectId}">
                                    selected="true"</c:if>>
                                ${sDTO.subjectName}
                            </option>
                        </c:forEach>
                    </select>
                    
                    <input type="submit" value="Create" name="btAction1"class="btn btn1"/>
                    <input type="hidden" value="${requestScope.SEARCH_NAME}" name="txtName" />
                    <input type="hidden" value="${requestScope.SEARCH_STATUS}" name="cbStatus" />
                    <input type="hidden" value="${requestScope.SEARCH_SUBJECT}" name="cbSubject" />
                    <input type="hidden" name="txtPage" value="${requestScope.SEARCH_PAGE}" />
                    
                    <c:url var="cancel" value="search">
                        <c:param name="txtName" value="${requestScope.SEARCH_NAME}"/>
                        <c:param name="cbStatus" value="${requestScope.SEARCH_STATUS}"/>
                        <c:param name="cbSubject" value="${requestScope.SEARCH_SUBJECT}"/>
                        <c:param name="btnPage" value="${requestScope.SEARCH_PAGE}"/>
                    </c:url>
                    <a href="${cancel}" class="btn btn1">Cancel</a>
                </form>
                <br/>

            </div>
        </div>
    </body>
</html>
