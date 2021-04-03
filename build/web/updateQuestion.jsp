<%-- 
    Document   : updateQuestion.jsp
    Created on : Feb 21, 2021, 8:31:11 PM
    Author     : Admin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
                <h1>UPDATE QUESTION</h1>
                <c:set var="user" value="${sessionScope.ACCOUNT_DTO}"/>
                <c:if test="${not empty user}">
                    <h3><font color="green">Welcome, ${user.name}</font></h3>
                    <h5><a href="logout">Logout</a></h5>
                </c:if>
            </section>
        </div>
        <c:set var="q" value="${requestScope.QUESTION}"/>
        <c:set var="a1" value="${requestScope.ANSWER0}"/>
        <c:set var="a2" value="${requestScope.ANSWER1}"/>
        <c:set var="a3" value="${requestScope.ANSWER2}"/>
        <c:set var="a" value="${requestScope.ANSWER_TRUE}"/>
        <div class="row">
            <div class="col-md-6 mx-auto">
                <c:set var="qErr" value="${requestScope.QERR}"/>
                <c:set var="aErr" value="${requestScope.AERR}"/>
                <form action="update-question" >
                    <input type="hidden" name="txtQuestionId" value="${requestScope.QUESTION_ID}" />
                    <p><strong>Question content:</strong></p> <textarea name="txtQuestionContent" class="form-control">${q.question_contect}</textarea>
                    <c:if test="${not empty qErr.questionContentLengthErr}">
                        <font color="red" class="asb">
                        ${qErr.questionContentLengthErr}
                        </font>
                    </c:if>  
                    <br/>
                   
                    <p><strong>Answer 1: </strong></p>
                    <input type="text" id="action" name="txtAnswer1" value="${a1.answer_content}" class="form-control" />
                    <input type="hidden" name="txtAnswer1Id" value="${a1.answerId}" />
                    <c:if test="${not empty aErr.answer1ContentLengthErr}">
                        <font color="red" class="asb">
                        ${aErr.answer1ContentLengthErr}
                        </font>
                    </c:if>    
                    <br/>
                    <p><strong>Answer 2: </strong></p>
                    <input type="text" id="action" name="txtAnswer2" value="${a2.answer_content}" class="form-control"/>
                    <input type="hidden" name="txtAnswer2Id" value="${a2.answerId}" />
                    <c:if test="${not empty aErr.answer2ContentLengthErr}">
                        <font color="red" class="asb">
                        ${aErr.answer2ContentLengthErr}
                        </font>
                    </c:if>
                    <br/>
                    <p><strong>Answer 3: </strong></p>
                    <input type="text" id="action" name="txtAnswer3" value="${a3.answer_content}" class="form-control"/>
                    <input type="hidden" name="txtAnswer3Id" value="${a3.answerId}" />
                    <c:if test="${not empty aErr.answer3ContentLengthErr}">
                        <font color="red" class="asb">
                        ${aErr.answer3ContentLengthErr}
                        </font>
                    </c:if>
                    <br/>
                    <p><strong>Correct answer:</strong></p> 
                    <input type="text" name="txtCorrectAnswer" value="${a.answer_content}" class="form-control"/>
                    <input type="hidden" name="txtCorrectAnswerId" value="${a.answerId}" />
                    <c:if test="${not empty aErr.answerCorrectContentLengthErr}">
                        <font color="red" class="asb">
                        ${aErr.answerCorrectContentLengthErr}
                        </font>
                    </c:if>
                    <br/>
                    <p><strong>Subject:</strong></p> 
                    <c:set var="sList" value="${requestScope.SUBJECT_LIST}"/>
                    <select name="cbSubjectUpdate" class="form-control">
                        <c:forEach var="sDTO" items="${sList}">
                            <option value="${sDTO.subjectId}" <c:if test="${q.subjectId == sDTO.subjectId}">
                                    selected="true"</c:if>>
                                ${sDTO.subjectName}
                            </option>
                        </c:forEach>
                    </select>
                    <br/>
                    <p><strong>Status:</strong></p>
                    <select name="cbStatusUpdate" class="form-control">
                        <option value="true" <c:if test="${q.status}">
                                selected="true"</c:if>>
                                Activate
                            </option>
                            <option value="false"<c:if test="${not q.status}">
                                selected="true"</c:if>>
                                Deactivate
                            </option>
                        </select>

                        <input type="submit" value="Update" name="btAction1"class="btn btn1"/>
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