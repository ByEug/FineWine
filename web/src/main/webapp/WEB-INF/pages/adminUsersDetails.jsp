<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="tags" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<html>
<head>
    <title>User №<c:out value="${user.id}"/></title>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-eOJMYsd53ii+scO/bJGFsiCZc+5NDVN2yr8+0RDqr0Ql0h+rP48ckxlpbzKgwra6" crossorigin="anonymous">
    <link rel="preconnect" href="https://fonts.gstatic.com">
    <link href="https://fonts.googleapis.com/css2?family=Roboto:wght@300;400;500;700&display=swap" rel="stylesheet">
    <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.15.3/css/all.css" integrity="sha384-SZXxX4whJ79/gErwcOYf+zWLeJdY/qpuqC4cAa9rOGUstPomtqpuNWT9wdPEn2fk" crossorigin="anonymous">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/styles/main.css">
</head>
<body>
<tags:headerWithoutCart/>
<div class="horizontal-divs">
    <div class="vertical-divs">
        <div>
            ${user.id}
        </div>
        <div>
            ${user.username}
        </div>
        <div>
            ${user.clientName} ${user.clientSurname}
        </div>
        <div>
            <a href="${pageContext.request.contextPath}/registration">Create new user</a>
        </div>
        <div>
            <form id="delete-user" method="post" action="${pageContext.request.contextPath}/admin/users/${user.id}/delete">
                <button form="delete-user">
                    Delete user
                </button>
            </form>
        </div>
    </div>
    <div class="vertical-divs">
        <div class="items-inline">
            <c:forEach var="role" items="${user.roles}">
                <div>${role.roleName.toString()}&nbsp</div>
            </c:forEach>
        </div>
        <form:form method="post" id="change-role" action="${pageContext.request.contextPath}/admin/users/${user.id}" commandName="addRoleDTO">
            <div>
                <label for="role-type">Choose new role:&nbsp</label>
                <form:select name="role" id="role-type" path="role">
                    <c:forEach items="${roles}" var="role">
                        <option>${role.toString()}</option>
                    </c:forEach>
                </form:select>
            </div>
            <div>
                <button class="btn btn-success" id="change-role-btn" form="change-role">
                    Choose role
                </button>
            </div>
        </form:form>
    </div>
</div>
<h4>Auth Logs:</h4>
<table class="no-border-table">
    <thead>
    <tr>
        <td class="no-border-td">
            Creating date
        </td>
        <td class="no-border-td">
            Action
        </td>
    </tr>
    </thead>
    <c:forEach var="authLog" items="${authLogList}">
        <tr>
            <td class="no-border-td">
                    ${authLog.creatingDate}
            </td>
            <td class="no-border-td">
                    ${authLog.action.toString()}
            </td>
        </tr>
    </c:forEach>
</table>
<h4>Auction Logs:</h4>
<table class="no-border-table">
    <thead>
    <tr>
        <td class="no-border-td">
            Creating date
        </td>
        <td class="no-border-td">
            Action
        </td>
        <td class="no-border-td">
            Auction id
        </td>
        <td class="no-border-td">
            Price
        </td>
    </tr>
    </thead>
    <c:forEach var="auctionLog" items="${auctionLogList}">
        <tr>
            <td class="no-border-td">
                    ${auctionLog.creatingDate}
            </td>
            <td class="no-border-td">
                    ${auctionLog.action.toString()}
            </td>
            <td class="no-border-td">
                    ${auctionLog.auction.id}
            </td>
            <td class="no-border-td">
                    ${auctionLog.auction.sellPrice} $
            </td>
        </tr>
    </c:forEach>
</table>
<h4>Inventory Logs:</h4>
<table class="no-border-table">
    <thead>
    <tr>
        <td class="no-border-td">
            Creating date
        </td>
        <td class="no-border-td">
            Action
        </td>
        <td class="no-border-td">
            Inventory item id
        </td>
    </tr>
    </thead>
    <c:forEach var="inventoryLog" items="${inventoryLogList}">
        <tr>
            <td class="no-border-td">
                    ${inventoryLog.creatingDate}
            </td>
            <td class="no-border-td">
                    ${inventoryLog.action.toString()}
            </td>
            <td class="no-border-td">
                    ${inventoryLog.inventoryItem.id}
            </td>
        </tr>
    </c:forEach>
</table>
<h4>Order Creating Logs:</h4>
<table class="no-border-table">
    <thead>
    <tr>
        <td class="no-border-td">
            Creating date
        </td>
        <td class="no-border-td">
            Order type
        </td>
        <td class="no-border-td">
            Order id
        </td>
        <td class="no-border-td">
            Total price
        </td>
    </tr>
    </thead>
    <c:forEach var="orderCreatingLog" items="${orderCreatingLogList}">
        <tr>
            <td class="no-border-td">
                    ${orderCreatingLog.creatingDate}
            </td>
            <td class="no-border-td">
                    ${orderCreatingLog.orderType.toString()}
            </td>
            <td class="no-border-td">
                    ${orderCreatingLog.order.id}
            </td>
            <td class="no-border-td">
                    ${orderCreatingLog.order.totalPrice} $
            </td>
        </tr>
    </c:forEach>
</table>
<tags:footer/>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta3/dist/js/bootstrap.bundle.min.js" integrity="sha384-JEW9xMcG8R+pH31jmWH6WWP0WintQrMb4s7ZOdauHnUtxwoG2vI5DkLtS3qm9Ekf" crossorigin="anonymous"></script>
</body>
</html>
