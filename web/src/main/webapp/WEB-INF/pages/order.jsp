<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib prefix="tags" tagdir="/WEB-INF/tags" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<html>
<head>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-eOJMYsd53ii+scO/bJGFsiCZc+5NDVN2yr8+0RDqr0Ql0h+rP48ckxlpbzKgwra6" crossorigin="anonymous">
    <link rel="preconnect" href="https://fonts.gstatic.com">
    <link href="https://fonts.googleapis.com/css2?family=Roboto:wght@300;400;500;700&display=swap" rel="stylesheet">
    <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.15.3/css/all.css" integrity="sha384-SZXxX4whJ79/gErwcOYf+zWLeJdY/qpuqC4cAa9rOGUstPomtqpuNWT9wdPEn2fk" crossorigin="anonymous">
    <title>Order</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/styles/main.css">
</head>
<body>
    <tags:header cart="${cart}"/>
    <div class="title">
        <div class="row">
            <div class="col align-self-center">
                <h2><b>Your order</b></h2>
            </div>
        </div>
    </div>
<%--    <div class="vertical-products-order">--%>
<%--        <c:forEach var="i" begin="0" end="${cart.cartItems.size() - 1}">--%>
<%--            <div class="horizontal-order">--%>
<%--                <div><img id="image" src="${cart.cartItems.get(i).product.picturePath}"></div>--%>
<%--                <div><c:out value="${cart.cartItems.get(i).product.productName}"/></div>--%>
<%--                <div><c:out value="${cart.cartItems.get(i).product.price}"/>$</div>--%>
<%--                <div>Quantity: <c:out value="${cart.cartItems.get(i).quantity}"/></div>--%>
<%--            </div>--%>
<%--        </c:forEach>--%>
<%--    </div>--%>
    <div class="container">
        <section class="row">
            <c:forEach var="i" begin="0" end="${cart.cartItems.size() - 1}">
                <div class="col-lg-4 col-sm-6 mb-3">
                    <div class="product-card">
                        <div class="product-thumb">
                            <a href="${pageContext.request.contextPath}/productDetails/${product.id}"><img src="${cart.cartItems.get(i).product.picturePath}" alt=""></a>
                        </div>
                        <div class="product-details">
                            <h4><a href="${pageContext.request.contextPath}/productDetails/${product.id}"><c:out value="${cart.cartItems.get(i).product.productName}"/></a></h4>
                            <div class="product-bottom-details d-flex justify-content-between">
                                <div class="product-price">
                                    <c:out value="${cart.cartItems.get(i).product.price}"/>$
                                </div>
                                <div class="product-links">
                                    Quantity: <c:out value="${cart.cartItems.get(i).quantity}"/>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </c:forEach>
        </section>
    </div>
<%--    <div class="vertical-divs">--%>
<%--        <div class="items-inline">--%>
<%--            <div><spring:theme code="subtotalOrder"/>&nbsp</div>--%>
<%--            <div><c:out value="${cart.totalCost}"/>$</div>--%>
<%--        </div>--%>
<%--        <div class="items-inline">--%>
<%--            <div><spring:theme code="deliveryOrder"/>&nbsp</div>--%>
<%--            <div><c:out value="${deliveryPrice}"/>$</div>--%>
<%--        </div>--%>
<%--        <div class="items-inline">--%>
<%--            <div><spring:theme code="totalOrder"/>&nbsp</div>--%>
<%--            <div><c:out value="${totalPrice}"/>$</div>--%>
<%--        </div>--%>
<%--    </div>--%>

    <table class="table">
        <thead>
        <tr>
            <th scope="col"><spring:theme code="subtotalOrder"/>&nbsp</th>
            <th scope="col"><spring:theme code="deliveryOrder"/>&nbsp</th>
            <th scope="col"><spring:theme code="totalOrder"/>&nbsp</th>
            <th scope="col">Your data</th>
            <c:if test="${currentOrderType == 'Delivery'}">
                <th>Delivery address:</th>
            </c:if>
            <th scope="col"></th>
        </tr>
        </thead>
        <tbody>
        <tr>
            <th scope="row"><c:out value="${cart.totalCost}"/>$</th>
            <td><c:out value="${deliveryPrice}"/>$</td>
            <td><c:out value="${totalPrice}"/>$</td>
<%--            <td>--%>
<%--                <div class="items-inline">--%>
<%--                    <div><spring:theme code="firstNameOrder"/>:&nbsp</div>--%>
<%--                    <div><c:out value="${order.firstName}"/></div>--%>
<%--                </div>--%>
<%--                <br>--%>
<%--                <div class="items-inline">--%>
<%--                    <div><spring:theme code="lastNameOrder"/>:&nbsp</div>--%>
<%--                    <div><c:out value="${order.lastName}"/></div>--%>
<%--                </div>--%>
<%--                <br>--%>
<%--                <div class="items-inline">--%>
<%--                    <div><spring:theme code="phoneOrder"/>:&nbsp</div>--%>
<%--                    <div><c:out value="${order.phoneNumber}"/></div>--%>
<%--                </div>--%>
<%--            </td>--%>
            <c:if test="${currentOrderType == 'Inventory'}">
                <td>
                <form:form method="post"
                           action="${pageContext.request.contextPath}/order/inventory?orderType=${currentOrderType}"
                           commandName="preOrderDataDTO">
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
                            <form:textarea path="additionalInformation" id="add-info-to-order"
                                           name="additionalInformation" placeholder="Additional information"
                                           maxlength="2000"/>
                        </div>
                    </div>
                    </td>

                    <%--                        <br>--%>
                    <td>
                        <button class="buttons">
                            Pay
                        </button>
                    </td>
                </form:form>
            </c:if>

            <c:if test="${currentOrderType == 'Delivery'}">
                <form:form method="post"
                           action="${pageContext.request.contextPath}/order/delivery?orderType=${currentOrderType}"
                           commandName="orderFullDataDTO">
                    <td>
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
                                <form:textarea path="additionalInformation" id="add-info-to-order"
                                               name="additionalInformation" placeholder="Additional information"
                                               maxlength="2000"/>
                            </div>
                        </div>
                    </td>

                    <td>
                        <div>
                            <div class="items-inline">
                                <div>
                                    <label for="country"><spring:theme code="countryOrder"/></label><span
                                        class="required">*</span>
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
                    </td>
                    <%--                <br>--%>

                    <td>
                        <button class="buttons">
                            Pay
                        </button>
                    </td>
                </form:form>
            </c:if>
        </tr>
        </tbody>
    </table>
    <br>
    <tags:footer/>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta3/dist/js/bootstrap.bundle.min.js" integrity="sha384-JEW9xMcG8R+pH31jmWH6WWP0WintQrMb4s7ZOdauHnUtxwoG2vI5DkLtS3qm9Ekf" crossorigin="anonymous"></script>
</body>
</html>
