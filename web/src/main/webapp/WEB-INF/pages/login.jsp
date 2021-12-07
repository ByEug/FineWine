<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="tags" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-eOJMYsd53ii+scO/bJGFsiCZc+5NDVN2yr8+0RDqr0Ql0h+rP48ckxlpbzKgwra6" crossorigin="anonymous">
    <link rel="preconnect" href="https://fonts.gstatic.com">
    <link href="https://fonts.googleapis.com/css2?family=Roboto:wght@300;400;500;700&display=swap" rel="stylesheet">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Log in</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/styles/main.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/styles/auth.css">
<body>
    <tags:headerLogin/>

    <section class="forms-section">
        <h1 class="section-title">Sign in with email</h1>
        <div class="forms">
            <div class="form-wrapper is-active">
                <button type="button" class="switcher switcher-login">
                    Login
                    <span class="underline"></span>
                </button>
                <form class="form form-login" action="<c:url value="/j_spring_security_check"/>" method="post">
                    <fieldset>
                        <legend>Please, enter your email and password for login.</legend>
                        <div class="input-block">
                            <label for="login-email">E-mail</label>
                            <input id="login-email" name="username_login" type="text" required />
                        </div>
                        <div class="input-block">
                            <label for="login-password">Password</label>
                            <input id="login-password" name="password_login" type="password" required />
                        </div>
                    </fieldset>
                    <button type="submit" class="btn-login">Login</button>
                </form>
                <div class="section-title">
                    <h3>
                        Still don't have an existing account?
                        <a class="nav-link" href="${pageContext.request.contextPath}/registration">Create an account</a>
                    </h3>
                </div>
            </div>
        </div>
    </section>

    <tags:footer/>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta3/dist/js/bootstrap.bundle.min.js" integrity="sha384-JEW9xMcG8R+pH31jmWH6WWP0WintQrMb4s7ZOdauHnUtxwoG2vI5DkLtS3qm9Ekf" crossorigin="anonymous"></script>
</body>
</html>
