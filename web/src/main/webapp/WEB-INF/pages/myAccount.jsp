<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib prefix="tags" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<html>
<head>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-eOJMYsd53ii+scO/bJGFsiCZc+5NDVN2yr8+0RDqr0Ql0h+rP48ckxlpbzKgwra6" crossorigin="anonymous">
    <link rel="preconnect" href="https://fonts.gstatic.com">
    <link href="https://fonts.googleapis.com/css2?family=Roboto:wght@300;400;500;700&display=swap" rel="stylesheet">
    <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.15.3/css/all.css" integrity="sha384-SZXxX4whJ79/gErwcOYf+zWLeJdY/qpuqC4cAa9rOGUstPomtqpuNWT9wdPEn2fk" crossorigin="anonymous">
    <title>My Account</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/styles/main.css">
</head>
<body>
<tags:header cart="${cart}"/>
<div class="title">
    <div class="row">
        <div class="col align-self-center">
            <h2><b><c:out value="${customUser.clientName} ${customUser.clientSurname}"/> account</b></h2>
            <h4><b><c:out value="Email: ${customUser.username}"/></b></h4>
        </div>
    </div>
</div>
<div class="row">
    <div class="col-md-4">
        <div class="product-card">
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
    </div>
    <div class="col-md-6">
        <div class="product-card">
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
</div>
<tags:footer/>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta3/dist/js/bootstrap.bundle.min.js" integrity="sha384-JEW9xMcG8R+pH31jmWH6WWP0WintQrMb4s7ZOdauHnUtxwoG2vI5DkLtS3qm9Ekf" crossorigin="anonymous"></script>
</body>
</html>
