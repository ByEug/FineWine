<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="tags" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>

<html>
<head>
    <title>Order №<c:out value="${order.id}"/></title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/styles/main.css">
</head>
<body>
    <tags:headerWithoutCart/>
    <h3>
        Order №<c:out value="${order.id}"/>
    </h3>
    <div class="vertical-products-order">
        <c:forEach var="i" begin="0" end="${order.orderItems.size() - 1}">
            <div class="horizontal-order">
                <div><img id="image" src="${order.orderItems.get(i).product.picturePath}"></div>
                <div><c:out value="${order.orderItems.get(i).product.productName}"/></div>
                <div><c:out value="${order.orderItems.get(i).product.price}"/>$</div>
                <div>Quantity: <c:out value="${order.orderItems.get(i).quantity}"/></div>
            </div>
        </c:forEach>
    </div>
    <div class="vertical-divs">
        <div class="items-inline">
            <div><spring:theme code="subtotalOrder"/>&nbsp</div>
            <div><c:out value="${order.subtotalPrice}"/>$</div>
        </div>
        <div class="items-inline">
            <div><spring:theme code="deliveryOrder"/>&nbsp</div>
            <div><c:out value="${order.deliveryPrice}"/>$</div>
        </div>
        <div class="items-inline">
            <div><spring:theme code="totalOrder"/>&nbsp</div>
            <div><c:out value="${order.totalPrice}"/>$</div>
        </div>
    </div>
    <div id="horizontal-order-overview">
        <div class="vertical-divs">
            <div>
                <h4>Customer data:</h4>
            </div>
            <div class="items-inline">
                <div><spring:theme code="firstNameOrder"/>:&nbsp</div>
                <div><c:out value="${order.firstName}"/></div>
            </div>
            <div class="items-inline">
                <div><spring:theme code="lastNameOrder"/>:&nbsp</div>
                <div><c:out value="${order.lastName}"/></div>
            </div>
            <div class="items-inline">
                <div><spring:theme code="phoneOrder"/>:&nbsp</div>
                <div><c:out value="${order.phoneNumber}"/></div>
            </div>
        </div>
        <c:if test="${order.address != null}">
            <div class="vertical-divs">
                <div>
                    <h4>Address:</h4>
                </div>
                <div class="items-inline">
                    <div><spring:theme code="countryOrder"/>:&nbsp</div>
                    <div><c:out value="${order.address.country.englishName}"/></div>
                </div>
                <div class="items-inline">
                    <div><spring:theme code="localityOrder"/>:&nbsp</div>
                    <div><c:out value="${order.address.locality}"/></div>
                </div>
                <div class="items-inline">
                    <div><spring:theme code="streetOrder"/>:&nbsp</div>
                    <div><c:out value="${order.address.street}"/></div>
                </div>
                <div class="items-inline">
                    <div><spring:theme code="homeNumberOrder"/>:&nbsp</div>
                    <div><c:out value="${order.address.homeNumber}"/></div>
                </div>
                <div class="items-inline">
                    <div><spring:theme code="flatNumberOrder"/>:&nbsp</div>
                    <div><c:out value="${order.address.flatNumber}"/></div>
                </div>
            </div>
        </c:if>
    </div>
    <div>
        <h3>
            Thanks for your order!
        </h3>
    </div>
    <form method="get" action="${pageContext.request.contextPath}/productList">
        <button class="buttons">
            Back to storefront
        </button>
    </form>
</body>
</html>
