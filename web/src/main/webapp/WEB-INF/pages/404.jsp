<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Error 404</title>
</head>
<body>
<h3>
    Error 404
</h3>
<h2>
    <c:if test="${not empty param.message}">
        <c:out value="${param.message}"/>
    </c:if>
</h2>
</body>
</html>
