<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="tags" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>

<html>
<head>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-eOJMYsd53ii+scO/bJGFsiCZc+5NDVN2yr8+0RDqr0Ql0h+rP48ckxlpbzKgwra6" crossorigin="anonymous">
    <link rel="preconnect" href="https://fonts.gstatic.com">
    <link href="https://fonts.googleapis.com/css2?family=Roboto:wght@300;400;500;700&display=swap" rel="stylesheet">
    <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.15.3/css/all.css" integrity="sha384-SZXxX4whJ79/gErwcOYf+zWLeJdY/qpuqC4cAa9rOGUstPomtqpuNWT9wdPEn2fk" crossorigin="anonymous">
    <title>Order №<c:out value="${order.id}"/></title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/styles/main.css">
</head>
<body>
    <tags:header cart="${cart}"/>
    <div class="title">
        <div class="row">
            <div class="col align-self-center">
                <h2><b>Order №<c:out value="${order.id}"/></b></h2>
            </div>
        </div>
    </div>
    <div class="container">
        <section class="row">
            <c:forEach var="i" begin="0" end="${order.orderItems.size() - 1}">
                <div class="col-lg-4 col-sm-6 mb-3">
                    <div class="product-card">
                        <div class="product-thumb">
                            <a href="${pageContext.request.contextPath}/productDetails/${order.orderItems.get(i).product.id}"><img src="${order.orderItems.get(i).product.picturePath}" alt=""></a>
                        </div>
                        <div class="product-details">
                            <h4><a href="${pageContext.request.contextPath}/productDetails/${order.orderItems.get(i).product.id}"><c:out value="${order.orderItems.get(i).product.productName}"/></a></h4>
                            <div class="product-bottom-details d-flex justify-content-between">
                                <div class="product-price">
                                    <c:out value="${order.orderItems.get(i).product.price}"/>$
                                </div>
                                <div class="product-links">
                                    Quantity: <c:out value="${order.orderItems.get(i).quantity}"/>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </c:forEach>
        </section>
    </div>

    <table class="table">
        <thead>
        <tr>
            <th scope="col"><spring:theme code="subtotalOrder"/>&nbsp</th>
            <th scope="col"><spring:theme code="deliveryOrder"/>&nbsp</th>
            <th scope="col"><spring:theme code="totalOrder"/>&nbsp</th>
            <th scope="col">Customer data</th>
            <c:if test="${order.address != null}">
                <th>Address</th>
            </c:if>
        </tr>
        </thead>
        <tbody>
        <tr>
            <th scope="row"><c:out value="${order.subtotalPrice}"/>$</th>
            <td><c:out value="${order.deliveryPrice}"/>$</td>
            <td><c:out value="${order.totalPrice}"/>$</td>
            <td>
                <div class="items-inline">
                    <div><spring:theme code="firstNameOrder"/>:&nbsp</div>
                    <div><c:out value="${order.firstName}"/></div>
                </div>
                <br>
                <div class="items-inline">
                    <div><spring:theme code="lastNameOrder"/>:&nbsp</div>
                    <div><c:out value="${order.lastName}"/></div>
                </div>
                <br>
                <div class="items-inline">
                    <div><spring:theme code="phoneOrder"/>:&nbsp</div>
                    <div><c:out value="${order.phoneNumber}"/></div>
                </div>
            </td>
            <c:if test="${order.address != null}">
                <td>
                    <div class="items-inline">
                        <div><spring:theme code="countryOrder"/>:&nbsp</div>
                        <div><c:out value="${order.address.country.englishName}"/></div>
                    </div>
                    <br>
                    <div class="items-inline">
                        <div><spring:theme code="localityOrder"/>:&nbsp</div>
                        <div><c:out value="${order.address.locality}"/></div>
                    </div>
                    <br>
                    <div class="items-inline">
                        <div><spring:theme code="streetOrder"/>:&nbsp</div>
                        <div><c:out value="${order.address.street}"/></div>
                    </div>
                    <br>
                    <div class="items-inline">
                        <div><spring:theme code="homeNumberOrder"/>:&nbsp</div>
                        <div><c:out value="${order.address.homeNumber}"/></div>
                    </div>
                    <br>
                    <div class="items-inline">
                        <div><spring:theme code="flatNumberOrder"/>:&nbsp</div>
                        <div><c:out value="${order.address.flatNumber}"/></div>
                    </div>
                </td>
            </c:if>
        </tr>
        </tbody>
    </table>

    <div class="title">
        <div class="row">
            <div class="col align-self-center">
                <h4><b>Thanks for your order!</b></h4>
            </div>
        </div>
    </div>
    <form method="get" action="${pageContext.request.contextPath}/productList">
        <button class="btn btn-dark">
            Back to storefront
        </button>
    </form>
    <tags:footer/>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta3/dist/js/bootstrap.bundle.min.js" integrity="sha384-JEW9xMcG8R+pH31jmWH6WWP0WintQrMb4s7ZOdauHnUtxwoG2vI5DkLtS3qm9Ekf" crossorigin="anonymous"></script>
</body>
</html>
