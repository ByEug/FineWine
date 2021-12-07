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
    <title>User inventory</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/styles/main.css">
</head>
<body>
<tags:header cart="${cart}"/>
<%--<h2><c:out value="${customUser.username}"/> account</h2>--%>
<%--<div class="vertical-divs">--%>
<%--    <c:forEach items="${inventoryItems}" var="inventoryItem">--%>
<%--        <div class="horizontal-divs">--%>
<%--            <div><img id="image" src="${inventoryItem.product.picturePath}"></div>--%>
<%--            <div><c:out value="${inventoryItem.product.productName}"/></div>--%>
<%--            <div><c:out value="${inventoryItem.product.price}"/>$</div>--%>
<%--            <div>Quantity: <c:out value="${inventoryItem.inventoryStock.stock}"/></div>--%>
<%--            <c:if test="${inventoryItem.onSell == false}">--%>
<%--                <form:form method="post" action="${pageContext.request.contextPath}/userInventory/sell/${inventoryItem.id}" commandName="addToMarketDTO">--%>
<%--                    <tags:validationRow label="choosePrice" id="choose-price${inventoryItem.id}" name="price"/>--%>
<%--                    <button class="buttons">--%>
<%--                        Add to market platform--%>
<%--                    </button>--%>
<%--                </form:form>--%>
<%--            </c:if>--%>
<%--            <c:if test="${inventoryItem.onSell == true}">--%>
<%--                <form:form method="post" action="${pageContext.request.contextPath}/userInventory/back/${inventoryItem.id}">--%>
<%--                    <div>--%>
<%--                        <button class="buttons">--%>
<%--                            Back to inventory--%>
<%--                        </button>--%>
<%--                    </div>--%>
<%--                </form:form>--%>
<%--            </c:if>--%>
<%--        </div>--%>
<%--    </c:forEach>--%>
<%--</div>--%>

<div class="title">
    <div class="row">
        <div class="col align-self-center">
            <h2><b><c:out value="${customUser.clientName} ${customUser.clientSurname}"/> account</b></h2>
        </div>
    </div>
</div>
<table class="table">
    <thead>
    <tr>
        <th scope="col">Product</th>
        <th scope="col">Name</th>
        <th scope="col">Price</th>
        <th scope="col">Quantity</th>
        <th scope="col">Actions</th>
    </tr>
    </thead>
    <tbody>
        <c:forEach items="${inventoryItems}" var="inventoryItem">
            <tr>
                <th scope="row">
                    <div class="product-thumb">
                        <a href="${pageContext.request.contextPath}/productDetails/${product.id}"><img class="img-fluid" src="${inventoryItem.product.picturePath}" alt=""></a>
                    </div>
                </th>
                <td><c:out value="${inventoryItem.product.productName}"/></td>
                <td><c:out value="${inventoryItem.product.price}"/>$</td>
                <td><c:out value="${inventoryItem.inventoryStock.stock}"/></td>
                <td>
                    <c:if test="${inventoryItem.onSell == false}">
                        <form:form method="post" action="${pageContext.request.contextPath}/userInventory/sell/${inventoryItem.id}" commandName="addToMarketDTO">
                            <tags:validationRow label="choosePrice" id="choose-price${inventoryItem.id}" name="price"/>
                            <button class="buttons">
                                Add to market platform
                            </button>
                        </form:form>
                    </c:if>
                    <c:if test="${inventoryItem.onSell == true}">
                        <form:form method="post" action="${pageContext.request.contextPath}/userInventory/back/${inventoryItem.id}">
                            <div>
                                <button class="buttons">
                                    Back to inventory
                                </button>
                            </div>
                        </form:form>
                    </c:if>
                </td>
            </tr>
        </c:forEach>
    </tbody>
</table>
<tags:footer/>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta3/dist/js/bootstrap.bundle.min.js" integrity="sha384-JEW9xMcG8R+pH31jmWH6WWP0WintQrMb4s7ZOdauHnUtxwoG2vI5DkLtS3qm9Ekf" crossorigin="anonymous"></script>
</body>
</html>
