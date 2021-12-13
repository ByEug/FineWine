<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="tags" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <title>Admin orders page</title>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-eOJMYsd53ii+scO/bJGFsiCZc+5NDVN2yr8+0RDqr0Ql0h+rP48ckxlpbzKgwra6" crossorigin="anonymous">
    <link rel="preconnect" href="https://fonts.gstatic.com">
    <link href="https://fonts.googleapis.com/css2?family=Roboto:wght@300;400;500;700&display=swap" rel="stylesheet">
    <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.15.3/css/all.css" integrity="sha384-SZXxX4whJ79/gErwcOYf+zWLeJdY/qpuqC4cAa9rOGUstPomtqpuNWT9wdPEn2fk" crossorigin="anonymous">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/styles/main.css">
</head>
<body>
<tags:headerWithoutCart/>
<h3>
    Orders
</h3>
<c:if test="${empty orders}">
    <h2>Orders are empty!</h2>
</c:if>
<c:if test="${not empty orders}">
    <table class="no-border-table">
        <thead>
        <tr>
            <td class="no-border-td">
                Order №
            </td>
            <td class="no-border-td">
                Customer name
            </td>
            <td class="no-border-td">
                Phone
            </td>
            <td class="no-border-td">
                Order type
            </td>
            <td class="no-border-td">
                Creating date
            </td>
            <td class="no-border-td">
                Total price
            </td>
            <td class="no-border-td">
                Order status
            </td>
        </tr>
        </thead>
        <c:forEach var="user" items="${orders}">
            <tr>
                <td class="no-border-td">
                    <a href="${pageContext.request.contextPath}/admin/orders/${user.id}"><c:out value="${user.id}"/></a>
                </td>
                <td class="no-border-td">
                    <c:out value="${user.firstName}"/> <c:out value="${user.lastName}"/>
                </td>
                <td class="no-border-td">
                    <c:out value="${user.phoneNumber}"/>
                </td>
                <td class="no-border-td">
                    <c:out value="${user.orderType.toString()}"/>
                </td>
                <td class="no-border-td">
                    <c:out value="${user.creatingDate}"/>
                </td>
                <td class="no-border-td">
                    <c:out value="${user.totalPrice}"/> $
                </td>
                <td class="no-border-td">
                    <c:out value="${user.orderStatus.toString()}"/>
                </td>
            </tr>
        </c:forEach>
    </table>
</c:if>
<tags:footer/>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta3/dist/js/bootstrap.bundle.min.js" integrity="sha384-JEW9xMcG8R+pH31jmWH6WWP0WintQrMb4s7ZOdauHnUtxwoG2vI5DkLtS3qm9Ekf" crossorigin="anonymous"></script>
</body>
</html>
