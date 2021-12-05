<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib prefix="tags" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<html>
<head>
    <title>User orders</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/styles/main.css">
</head>
<body>
<tags:header cart="${cart}"/>
<h2><c:out value="${customUser.username}"/> account</h2>
<div class="vertical-divs">
    <c:forEach var="order" items="${userOrders}">
        <div>
            <a href="${pageContext.request.contextPath}/orderOverview/${order.id}">Order №<c:out value="${order.id}"/></a>
        </div>
    </c:forEach>
</div>
</body>
</html>
