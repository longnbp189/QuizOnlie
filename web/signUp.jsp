<%-- 
    Document   : SignUp
    Created on : Jan 23, 2021, 8:53:16 AM
    Author     : Admin
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Sign Up</title>
        <link href="<c:url value="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" />" rel="stylesheet">
        <style>
            <%@include file="css/mystyle.css"%>
            body{
                background: grey;
            }
            form{
                width:  450px;
                border: 3px solid #f1f1f1;
                padding: 20px;
                left: 35%;
                top: 8%;
                position: absolute;
                background: white;
                height: 600px;
                border-radius: 10px;
            }
            .abs{
                position: absolute;
                left: 30px;
            }
        </style>
    </head>
    <body class="text-center">
        <form action="sign-up" method="POST">
            <h1>Create new account</h1>
            <c:set var="errors" value="${requestScope.CREATEERROR}"/>
            <p><strong>Username*: </strong></p>
            <input type="text" name="txtEmail" value="" class="form-control" placeholder="Username (6 - 50 chars)" required=""/>  
            <c:if test="${not empty errors.emailLengthError}">
                <font color="red" class="asb">
                ${errors.emailLengthError}
                </font>
            </c:if>
            <c:if test="${not empty errors.emailFormErr}">
                <font color="red" class="asb">
                ${errors.emailFormErr}
                </font>
            </c:if>
            <c:if test="${not empty errors.emailExistError}">
                <font color="red" class="asb">
                ${errors.emailExistError}
                </font>
            </c:if>
            <br/>
            <p><strong>Password*: </strong></p>
            <input type="password" name="txtPassword" value="" class="form-control" placeholder="Password (6 - 20 chars)" required=""/>
            <c:if test="${not empty errors.passwordLengthError}">
                <font color="red" class="asb">
                ${errors.passwordLengthError}
                </font>
            </c:if>
            <br/>
            <p><strong>Confirm*: </strong></p>
            <input type="password" name="txtConfirm" value="" class="form-control" placeholder="Confirm passwoord" required=""/>
            <c:if test="${not empty errors.confirmPasswordError}">
                <font color="red" class="asb">
                ${errors.confirmPasswordError}
                </font>
            </c:if>
            <br/>
            <p><strong>Full name*: </strong></p>
            <input type="text" name="txtName" value="${param.nameLengthError}" class="form-control" placeholder="Full name (2 - 50 chars)" required=""/>
            <c:if test="${not empty errors.nameLengthError}">
                <font color="red" class="asb">
                ${errors.nameLengthError}
                </font>
            </c:if>
            <br/>
            <input type="submit" value="Creat New Account" name="btAction" class="btn btn1"/>
            <input type="reset" value="Reset" class="btn btn2"/>
            <br/>
            <a href="try" style="font-size: 20px">Cancel</a>
        </form>
    </body>
</html>
