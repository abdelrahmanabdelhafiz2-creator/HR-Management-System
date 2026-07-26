<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%
    String contextPath = request.getContextPath();
%>

<!DOCTYPE html>
<html lang="en">

<head>

    <meta charset="UTF-8">

    <meta name="viewport" content="width=device-width, initial-scale=1.0">

    <title>HR Management System</title>

    <link rel="stylesheet" type="text/css" href="<%=contextPath%>/css/style.css">

</head>

<body>

<header class="header">

    <div class="logo">

        <a href="<%=contextPath%>/dashboard" class="logo-link">
            HR System
        </a>

    </div>

    <nav class="navbar">

        <a href="<%=contextPath%>/dashboard">
            Dashboard
        </a>

        <a href="<%=contextPath%>/employee">
            Employees
        </a>

        <a href="<%=contextPath%>/department">
            Departments
        </a>

        <a href="<%=contextPath%>/promotion">
            Promotions
        </a>

        <a href="<%=contextPath%>/bonus">
            Bonuses
        </a>

        <a href="<%=contextPath%>/feedback">
            Feedback
        </a>

    </nav>

    <div class="user-box">

        <%
            dto.UserDTO loggedUser = (dto.UserDTO) session.getAttribute("loggedUser");

            if (loggedUser != null) {
        %>

        <span>
            <%= util.Escape.html(loggedUser.getName()) %>
        </span>

        <span>
            HR Admin
        </span>

        <%
        } else {
        %>

        <span>
            Guest
        </span>

        <span>
            Visitor
        </span>

        <%
            }
        %>

        <a class="logout-btn" href="<%=contextPath%>/logout">
            Logout
        </a>

    </div>

</header>

<div class="page-space"></div>
