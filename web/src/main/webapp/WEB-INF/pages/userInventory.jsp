<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib prefix="tags" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<html>
<head>
    <title>User inventory</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/styles/main.css">
</head>
<body>
<tags:header cart="${cart}"/>
<h2><c:out value="${customUser.username}"/> account</h2>
<div class="vertical-divs">
    <c:forEach items="${inventoryItems}" var="inventoryItem">
        <div class="horizontal-divs">
            <div><img id="image" src="${inventoryItem.product.picturePath}"></div>
            <div><c:out value="${inventoryItem.product.productName}"/></div>
            <div><c:out value="${inventoryItem.product.price}"/>$</div>
            <div>Quantity: <c:out value="${inventoryItem.inventoryStock.stock}"/></div>
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
        </div>
    </c:forEach>
</div>
</body>
</html>
