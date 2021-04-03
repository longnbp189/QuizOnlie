<%-- 
    Document   : takeQuiz
    Created on : Jan 23, 2021, 6:03:18 PM
    Author     : Admin
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
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

        <title>Quiz</title>
    </head>
    <body class="text-center">
        <div class="cover-container p-3 mx-auto">
            <section class="jumbotron" style="background: #e8e9f2">
                <div class="container">
                    <h1>Do quiz</h1>
                    <c:set var="user" value="${sessionScope.ACCOUNT_DTO}"/>
                    <c:if test="${not empty user}">
                        <h3><font color="green">Welcome, ${user.name}</font></h3>
                        <h5><a href="logout">Logout</a></h5>
                    </c:if>
                </div>
            </section>

            <div class="row">
                <form action="select-question" method="POST" class="col-md-12">
                    <div class="row">
                        <div class="col-md-3">
                            <div class="col-md-12 mx-auto">
                                <p id="time" style="color: white"> ${sessionScope.TIME}</p>
                                <p><strong>Timer: </strong></p><p id="demo" ></p>
                            </div>
                        </div>
                                <div class="col-md-5" style="margin-left: 75px">
                            <c:set var="q" value="${sessionScope.QUESTION}"/>
                            <h5>Question number ${sessionScope.NUM}: </h5>
                            <br/>
                            <p><strong>${q.question_contect}</strong></p>
                            <br/>
                            <c:set var="aList" value="${q.answers}"/>
                            <c:forEach var="a" items="${aList}">
                                <p><input type="radio" name="radAnswer" value="${a.answerId}"
                                          <c:if test="${q.studentAnswer == a.answerId}">
                                              checked="true"
                                          </c:if>/>
                                    ${a.answer_content}</p>
                                </c:forEach>
                            <br/>
                        </div>
                    </div>
                    <div class="bottom">

                        <c:set var="numValue" value="${sessionScope.NUM}"/>
                        <input type="hidden" name="txtQuestionId" value="${q.questionId}" />
                        <c:if test="${numValue=='1'}">
                            <input type="submit" value="Previous" disabled="true" name="btnNum" class="btn btn2"/>
                        </c:if>
                        <c:if test="${numValue!='1'}">
                            <input type="submit" value="Previous" name="btnNum" class="btn btn2"/>
                        </c:if>
                        <c:forEach begin="1" end="10" var="num">
                            <c:if test="${numValue==num}">
                                <input type="submit" value="${num}" disabled="true" name="btnNum" class="btn btn2"/>
                            </c:if>
                            <c:if test="${numValue!=num}">
                                <input type="submit" value="${num}" name="btnNum" class="btn btn2"/>
                            </c:if>
                        </c:forEach>
                        <c:if test="${numValue=='10'}">
                            <input type="submit" value="Next" disabled="true"name="btnNum" class="btn btn2"/>
                        </c:if>
                        <c:if test="${numValue!='10'}">
                            <input type="submit" value="Next" name="btnNum" class="btn btn2"/>
                        </c:if>
                    </div>
                </form>
            </div>
        </div>

        <div class="bottom">
            <form action="submit-exam" method="POST" >
                <input type="button" value="Submit" class="btn btn1" style="padding: 10px 40px; font-size: 20px" data-toggle="modal" data-target="#exampleModalLong"/>

                <div class="modal fade" id="exampleModalLong" tabindex="-1" role="dialog" aria-labelledby="exampleModalLongTitle" aria-hidden="true">
                    <div class="modal-dialog" role="document">
                        <div class="modal-content">
                            <div class="modal-header">
                                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                                    <span aria-hidden="true">&times;</span>
                                </button>
                            </div>
                            <div class="modal-body">
                                Do you want to submit?
                            </div>
                            <div class="modal-footer">
                                <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
                                <input type="submit" value="Submit" name="btAction" id="submit" class="btn btn1"/>
                            </div>
                        </div>
                    </div>
                </div>
            </form>
        </div>
        <script>
            // cập nhập thời gian sau mỗi 1 giây
            var x = setInterval(function () {
                // Thiết lập thời gian đích mà ta sẽ đếm
                time = document.getElementById("time").innerHTML;
                var countDownDate = new Date(time).getTime() + (60 * 5 * 1000);
                // Lấy thời gian hiện tại
                var now = new Date().getTime();

                // Lấy số thời gian chênh lệch
                var distance = countDownDate - now;

                // Tính toán số ngày, giờ, phút, giây từ thời gian chênh lệch
                var minutes = Math.floor((distance % (1000 * 60 * 60)) / (1000 * 60));
                var seconds = Math.floor((distance % (1000 * 60)) / 1000);

                // HIển thị chuỗi thời gian trong thẻ p
                document.getElementById("demo").innerHTML = minutes + " Minutes " + seconds + " Seconds ";
                // Nếu thời gian kết thúc, hiển thị chuỗi thông báo
                if (distance < 0) {
                    clearInterval(x);
                    document.getElementById("demo").innerHTML = "Time over";
                    document.getElementById("submit").click();
                }
            }, 1000);
        </script>        

    </body>
</html>
