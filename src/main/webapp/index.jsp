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

<header class="home-header">

    <div class="home-logo">
        HR System
    </div>

    <nav class="home-nav">

        <a href="#features">Features</a>

        <a href="#about">About</a>

        <a href="<%=contextPath%>/views/login.jsp" class="home-login-btn">
            Login
        </a>

    </nav>

</header>

<section class="hero">

    <div class="hero-content">

        <h1>
            Human Resources Management System
        </h1>

        <p>
            Manage employees, departments, promotions,
            bonuses and performance from one place.
        </p>

        <a href="<%=contextPath%>/views/login.jsp" class="hero-btn">
            Login to System
        </a>

    </div>

</section>

<section id="features" class="features">

    <h2>System Features</h2>

    <div class="feature-grid">

        <div class="feature-card">

            <h3>Employees</h3>

            <p>Manage employee information easily.</p>

        </div>

        <div class="feature-card">

            <h3>Departments</h3>

            <p>Organize all company departments.</p>

        </div>

        <div class="feature-card">

            <h3>Promotions</h3>

            <p>Track employee promotions.</p>

        </div>

        <div class="feature-card">

            <h3>Bonuses & Feedback</h3>

            <p>Improve employee performance.</p>

        </div>

    </div>

</section>

<section id="about" class="about">

    <h2>About HR System</h2>

    <p>
        HR Management System is designed to simplify
        employee management, department organization,
        promotions, bonuses and performance evaluation.
    </p>

</section>

<footer class="home-footer">

    <p>HR System &copy; 2026</p>

    <p>Enterprise Human Resources Management System</p>

</footer>

</body>

</html>
