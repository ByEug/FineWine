<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib prefix="tags" tagdir="/WEB-INF/tags" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<html>
<head>
    <link rel="stylesheet"
          href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.0/dist/css/bootstrap.min.css"
          integrity="sha384-B0vP5xmATw1+K9KRQjQERJvTumQW0nPEzvF6L/Z6nronJ3oUOFUFpCjEUQouq2+l"
          crossorigin="anonymous">
    <title>Cart</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/styles/main.css">
</head>
<body>
    <tags:header cart="${cart}"/>
    <h3>
        Your cart:
    </h3>
    <c:if test="${not empty isEmpty}">
        <h3>Your cart is empty!</h3>
    </c:if>
    <c:if test="${empty isEmpty}">
        <form:form id="update-form" method="post" action="${pageContext.request.contextPath}/cart" commandName="productArrayDTO">
            <div id="vertical-products-cart">
                <c:forEach var="i" begin="0" end="${cart.cartItems.size() - 1}">
                    <div id="horizontal-cart">
                        <div><img id="image" src="${cart.cartItems.get(i).product.picturePath}"></div>
                        <div><c:out value="${cart.cartItems.get(i).product.productName}"/></div>
                        <div><c:out value="${cart.cartItems.get(i).product.price}"/>$</div>
                        <div>
                            <form:input path="productDTOItems[${i}].id" type="hidden"/>
                            <form:input path="productDTOItems[${i}].quantity"/>
                            <br>
                            <form:errors path="productDTOItems[${i}].quantity" cssClass="red-res"/>
                            <span class="green-res"><c:out value="${fn:contains(updatedProductIds, cart.cartItems.get(i).product.id) ? successfulUpdateMessage : ''}"/></span>
                        </div>
                        <div>
                            <button class="buttons" formmethod="post" formaction="${pageContext.request.contextPath}/cart/${cart.cartItems.get(i).product.id}">
                                Delete from cart
                            </button>
                        </div>
                    </div>
                </c:forEach>
            </div>
        </form:form>
        <form id="to-order-form" action="${pageContext.request.contextPath}/order">
        </form>
        <div id="cart-buttons">
            <button class="buttons" id="update-cart" form="update-form">
                Update
            </button>
            <button class="buttons" id="order-cart" form="to-order-form">
                Order
            </button>
        </div>
    </c:if>
    <tags:footer/>
</body>
</html>
