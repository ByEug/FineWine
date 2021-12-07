<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="tags" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>

<html>
<head>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-eOJMYsd53ii+scO/bJGFsiCZc+5NDVN2yr8+0RDqr0Ql0h+rP48ckxlpbzKgwra6" crossorigin="anonymous">
    <link rel="preconnect" href="https://fonts.gstatic.com">
    <link href="https://fonts.googleapis.com/css2?family=Roboto:wght@300;400;500;700&display=swap" rel="stylesheet">
    <title>Registration</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/styles/main.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/styles/auth.css">
</head>
<body>
<tags:headerLogin/>
<form:form method="post" action="${pageContext.request.contextPath}/registration" commandName="customUserDTO">
<section class="forms-section">
    <h1 class="section-title">Create account!</h1>
    <div class="forms">
        <div class="form-wrapper is-active">
            <button type="button" class="switcher switcher-signup">
                Sign Up
                <span class="underline"></span>
            </button>
                <form class="form form-login">
                    <fieldset>
                        <div class="input-block">
                            <tags:validationRow label="firstNameRegistration" id="first-name" name="clientName"/>
                        </div>
                        <div class="input-block">
                            <tags:validationRow label="lastNameRegistration" id="last-name" name="clientSurname"/>
                        </div>
                        <div class="input-block">
                            <tags:validationRow label="usernameRegistration" id="username" name="username"/>
                        </div>
                        <div class="input-block">
                            <tags:validationRow label="passwordRegistration" id="password" name="password"/>
                        </div>
                    </fieldset>
                    <button type="submit" class="btn-login">Continue</button>
                </form>
            <div class="section-title">
                <h3>
                    Do you already have an account?
                    <a class="nav-link" href="${pageContext.request.contextPath}/login">Login</a>
                </h3>
            </div>
        </div>
    </div>
</section>
</form:form>
<tags:footer/>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta3/dist/js/bootstrap.bundle.min.js" integrity="sha384-JEW9xMcG8R+pH31jmWH6WWP0WintQrMb4s7ZOdauHnUtxwoG2vI5DkLtS3qm9Ekf" crossorigin="anonymous"></script>
</body>
</html>
