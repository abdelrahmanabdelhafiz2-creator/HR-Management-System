<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%
    String contextPath = request.getContextPath();
%>

<!DOCTYPE html>
<html lang="en">

<head>

    <meta charset="UTF-8">

    <meta name="viewport" content="width=device-width, initial-scale=1.0">

    <title>Login - HR Management System</title>

    <link rel="stylesheet" type="text/css" href="<%=contextPath%>/css/style.css">

</head>

<body>

<main class="login-page">

    <div class="login-card">

        <h1>
            HR Management System
        </h1>

        <p>
            Sign in to continue
        </p>

        <form action="<%=contextPath%>/login" method="post">

            <div class="form-group">

                <label for="email">
                    Email
                </label>

                <input type="email" id="email" name="email" required>

            </div>

            <div class="form-group">

                <label for="password">
                    Password
                </label>

                <input type="password" id="password" name="password" required>

            </div>

            <button type="submit" class="btn-login">
                Login
            </button>

        </form>

    </div>

</main>

</body>

</html>
