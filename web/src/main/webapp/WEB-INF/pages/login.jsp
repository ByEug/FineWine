<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="tags" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <title>Log in</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/styles/main.css">
</head>
<body>
<tags:headerLogin/>
<div id="login-error">
    <c:if test="${not empty error}">
        ${error}
    </c:if>
</div>
<form name="form_login" action="<c:url value="/j_spring_security_check"/>" method="post">
    <table class="no-border-table">
        <tr>
            <td class="no-border-td">Email:</td>
            <td class="no-border-td">
                <input type="text" name="username_login" value="">
            </td>
        </tr>
        <tr>
            <td class="no-border-td">Password:</td>
            <td class="no-border-td">
                <input type="password" name="password_login" value="">
            </td>
        </tr>
        <tr>
            <td class="no-border-td">
                <button class="buttons">
                    Submit
                </button>
            </td>
        </tr>
    </table>
</form>
<div>
    <h3>
        Still don't have an existing account?
        <a href="${pageContext.request.contextPath}/registration">Create an account</a>
    </h3>
</div>
</body>
</html>
