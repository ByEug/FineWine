<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Error 404</title>
    <link rel="stylesheet"
          href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.0/dist/css/bootstrap.min.css"
          integrity="sha384-B0vP5xmATw1+K9KRQjQERJvTumQW0nPEzvF6L/Z6nronJ3oUOFUFpCjEUQouq2+l"
          crossorigin="anonymous">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/styles/main.css">
<body>
    <tags:header cart="${cart}"/>
    <div class="white-box container">
        <div style="text-align: center">
            <img src="${pageContext.request.contextPath}/images/img404.jpg" style="max-width:600px; height:auto" alt='[]' />
        </div>
        <div style="text-align: center">
            <a class="button" style="display: inline-block; margin: auto;" href="/productList">Go back to homepage</a>
        </div>
    </div>
    <%--<h3>--%>
    <%--    Error 404--%>
    <%--</h3>--%>
    <h2>
        <c:if test="${not empty param.message}">
            <c:out value="${param.message}"/>
        </c:if>
    </h2>
    <tags:footer/>
</body>
</html>
