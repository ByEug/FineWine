<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib prefix="tags" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<html>
<head>
    <title>Trading platform</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/styles/main.css">
</head>
<body>
<tags:header cart="${cart}"/>
<h2>Trading platform:</h2>
<c:if test="${errorNotEnoughFunds != null}">
    <div class="red-res">
        <h2>${errorNotEnoughFunds}</h2>
    </div>
</c:if>
<div class="vertical-divs">
    <c:forEach items="${liveAuctions}" var="auction">
        <div class="horizontal-divs">
            <div><img id="image" src="${auction.inventoryItem.product.picturePath}"></div>
            <div class="vertical-divs">
                <div>
                    Seller: ${customUser.username}
                </div>
                <div>
                    <c:out value="${auction.inventoryItem.product.productName}"/>
                </div>
            </div>
            <div><c:out value="${auction.sellPrice}"/>$</div>
            <div>Quantity: <c:out value="${auction.inventoryItem.inventoryStock.stock}"/></div>
            <c:if test="${customUser.id == auction.seller.id}">
                <form:form method="post" action="${pageContext.request.contextPath}/tradingPlatform/back/${auction.inventoryItem.id}">
                    <div>
                        <button class="buttons">
                            Back to inventory
                        </button>
                    </div>
                </form:form>
            </c:if>
            <c:if test="${customUser.id != auction.seller.id}">
                <form:form method="post" action="${pageContext.request.contextPath}/tradingPlatform/buy/${auction.inventoryItem.id}">
                    <div class="vertical-divs">
                        <div>
                            <button class="buttons">
                                Buy
                            </button>
                        </div>
                    </div>
                </form:form>
            </c:if>
        </div>
    </c:forEach>
</div>
</body>
</html>
