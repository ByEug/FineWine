<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="tags" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <script
            src="//maxcdn.bootstrapcdn.com/bootstrap/4.1.1/js/bootstrap.min.js"></script>
    <script
            src="//cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
    <link
            href="//maxcdn.bootstrapcdn.com/bootstrap/4.1.1/css/bootstrap.min.css"
            rel="stylesheet" id="bootstrap-css">
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
<%--    <title>Log in</title>--%>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/styles/main.css">
    <link rel="stylesheet"
          href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
    <script
            src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
    <script
            src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
    <script
            src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
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
    <tags:footer/>
</body>
</html>
