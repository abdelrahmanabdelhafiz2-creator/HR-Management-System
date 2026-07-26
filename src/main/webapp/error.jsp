<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%
    String contextPath = request.getContextPath();
%>

<!DOCTYPE html>
<html lang="en">

<head>

    <meta charset="UTF-8">

    <meta name="viewport" content="width=device-width, initial-scale=1.0">

    <title>Error - HR Management System</title>

    <link rel="stylesheet" type="text/css" href="<%=contextPath%>/css/style.css">

</head>

<body>

<main class="error-page">

    <div class="error-card">

        <h1>Oops! Something Went Wrong</h1>

        <p>
            We encountered an error while processing your request.
            Please try again or contact support.
        </p>

        <a href="<%=contextPath%>/dashboard" class="btn-home">
            Go to Dashboard
        </a>

        <a href="<%=contextPath%>/views/login.jsp" class="btn-login-error">
            Login Again
        </a>

    </div>

</main>

</body>

</html>
