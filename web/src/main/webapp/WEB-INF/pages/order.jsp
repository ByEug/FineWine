<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib prefix="tags" tagdir="/WEB-INF/tags" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<html>
<head>
    <title>Order</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/styles/main.css">
</head>
<body>
    <tags:header cart="${cart}"/>
    <h3>
        Your order:
    </h3>
    <div class="vertical-products-order">
        <c:forEach var="i" begin="0" end="${cart.cartItems.size() - 1}">
            <div class="horizontal-order">
                <div><img id="image" src="${cart.cartItems.get(i).product.picturePath}"></div>
                <div><c:out value="${cart.cartItems.get(i).product.productName}"/></div>
                <div><c:out value="${cart.cartItems.get(i).product.price}"/>$</div>
                <div>Quantity: <c:out value="${cart.cartItems.get(i).quantity}"/></div>
            </div>
        </c:forEach>
    </div>
    <div class="vertical-divs">
        <div class="items-inline">
            <div><spring:theme code="subtotalOrder"/>&nbsp</div>
            <div><c:out value="${cart.totalCost}"/>$</div>
        </div>
        <div class="items-inline">
            <div><spring:theme code="deliveryOrder"/>&nbsp</div>
            <div><c:out value="${deliveryPrice}"/>$</div>
        </div>
        <div class="items-inline">
            <div><spring:theme code="totalOrder"/>&nbsp</div>
            <div><c:out value="${totalPrice}"/>$</div>
        </div>
    </div>
    <br>
    <c:if test="${currentOrderType == 'Inventory'}">
        <form:form method="post" action="${pageContext.request.contextPath}/order/inventory?orderType=${currentOrderType}" commandName="preOrderDataDTO">
            <div class="vertical-divs">
                <div>
                    <tags:validationRow label="firstNameOrder" id="first-name" name="firstName"/>
                </div>
                <div>
                    <tags:validationRow label="lastNameOrder" id="last-name" name="lastName"/>
                </div>
                <div>
                    <tags:validationRow label="phoneOrder" id="phone" name="phone"/>
                </div>
                <div>
                    <form:textarea path="additionalInformation" id="add-info-to-order" name="additionalInformation" placeholder="Additional information" maxlength="2000"/>
                </div>
            </div>
            <br>
            <button class="buttons">
                Pay
            </button>
        </form:form>
    </c:if>
    <c:if test="${currentOrderType == 'Delivery'}">
        <form:form method="post" action="${pageContext.request.contextPath}/order/delivery?orderType=${currentOrderType}" commandName="orderFullDataDTO">
            <div class="vertical-divs">
                <div>
                    <tags:validationRow label="firstNameOrder" id="first-name" name="firstName"/>
                </div>
                <div>
                    <tags:validationRow label="lastNameOrder" id="last-name" name="lastName"/>
                </div>
                <div>
                    <tags:validationRow label="phoneOrder" id="phone" name="phone"/>
                </div>
                <div>
                    <form:textarea path="additionalInformation" id="add-info-to-order" name="additionalInformation" placeholder="Additional information" maxlength="2000"/>
                </div>
                <div>
                    <h4>Delivery address:</h4>
                </div>
                <div>
                    <div class="items-inline">
                        <div>
                            <label for="country"><spring:theme code="countryOrder"/></label><span class="required">*</span>
                        </div>
                        <div>
                            <form:select path="addressDTO.country" id="country">
                                <c:forEach items="${countries}" var="country">
                                    <form:option value="${country.englishName}"/>
                                </c:forEach>
                            </form:select>
                            <br>
                            <form:errors path="addressDTO.country" cssClass="red-res"/>
                        </div>
                    </div>
                </div>
                <div>
                    <tags:validationRow label="localityOrder" id="locality" name="addressDTO.locality"/>
                </div>
                <div>
                    <tags:validationRow label="streetOrder" id="street" name="addressDTO.street"/>
                </div>
                <div>
                    <tags:validationRow label="homeNumberOrder" id="home-number" name="addressDTO.homeNumber"/>
                </div>
                <div>
                    <tags:validationRow label="flatNumberOrder" id="flat-number" name="addressDTO.flatNumber"/>
                </div>
            </div>
            <br>
            <button class="buttons">
                Pay
            </button>
        </form:form>
    </c:if>
</body>
</html>
