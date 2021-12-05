<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="tags" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>

<html>
<head>
    <title>Registration</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/styles/main.css">
</head>
<body>
<tags:headerLogin/>
<h3>
    Registration form:
</h3>
<form:form method="post" action="${pageContext.request.contextPath}/registration" commandName="customUserDTO">
    <div class="vertical-divs">
        <div>
            <tags:validationRow label="firstNameRegistration" id="first-name" name="clientName"/>
        </div>
        <div>
            <tags:validationRow label="lastNameRegistration" id="last-name" name="clientSurname"/>
        </div>
        <div>
            <tags:validationRow label="usernameRegistration" id="username" name="username"/>
        </div>
        <div>
            <tags:validationRow label="passwordRegistration" id="password" name="password"/>
        </div>
    </div>
    <br>
    <button class="buttons">
        Register
    </button>
</form:form>
</body>
</html>
