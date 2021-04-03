<%-- 
    Document   : login
    Created on : Jan 23, 2021, 1:40:33 PM
    Author     : Admin
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href="<c:url value="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" />" rel="stylesheet">
        <title>Login Page</title>
        <style>
            <%@include file="css/mystyle.css"%>
            body{
                background: grey;
            }
            form{
                width:  350px;
                border: 3px solid #f1f1f1;
                padding: 20px;
                left: 38%;
                top: 15%;
                position: absolute;
                background: white;
                height: 475px;
                border-radius: 10px;
            }
        </style>
    </head>
    <body class="text-center">
        <form action="login" method="POST">
            <h1>Login</h1>
            <p><strong> Email: </strong></p>
            <input type="text" name="txtEmail" value="" placeholder="Email" required autofocus class="form-control"/><br/>
            <p><strong> Password: </strong></p>
            <input type="password" name="txtPassword" value="" placeholder="Password" required class="form-control"/><br/>
            <input type="submit" value="Login" name="btAction" class="btn btn1"/>
            <br/>
            <a href="sign-up">Sign Up</a> <br/>
            <c:if test="${not empty sessionScope.LOGIN_ERROR}">
                <font color="red">
                ${sessionScope.LOGIN_ERROR}
                </font>
            </c:if>
        </form>
    </body>
</html>
