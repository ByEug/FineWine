<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib prefix="tags" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<html>
<head>
    <title>My Account</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/styles/main.css">
</head>
<body>
<tags:header cart="${cart}"/>
<h2><c:out value="${customUser.username}"/> account</h2>
<div class="horizontal-divs">
    <div class="vertical-divs">
        <div>
            <h3>
                <a href="${pageContext.request.contextPath}/userOrders">My Orders</a>
            </h3>
        </div>
        <div>
            <h3>
                <a href="${pageContext.request.contextPath}/userInventory">My Inventory</a>
            </h3>
        </div>
    </div>
    <div class="vertical-divs">
        <div class="items-inline">
            <h3>Your funds: </h3>
            <h2>${customUser.currentFundsBalance}$</h2>
        </div>
        <div class="items-inline">
            <form:form method="post" action="${pageContext.request.contextPath}/myAccount" commandName="addFundsDTO">
                <tags:validationRow label="addFunds" id="add-funds" name="funds"/>
                <button class="buttons">
                    Add funds
                </button>
            </form:form>
        </div>
        <div>
            <h3>
                <a href="${pageContext.request.contextPath}/tradingPlatform">Trading Platform</a>
            </h3>
        </div>
    </div>
</div>
</body>
</html>
