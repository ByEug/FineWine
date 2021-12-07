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
    <title>Cart</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/styles/main.css">
<%--    <link rel="stylesheet" href="${pageContext.request.contextPath}/styles/cart.css">--%>
</head>
<body>
    <tags:header cart="${cart}"/>

    <c:if test="${not empty isEmpty}">
        <div class="container-fluid mt-100">
            <div class="row">
                <div class="col-md-12">
                    <div class="card">
<%--                        <div class="card-header">--%>
<%--                            <h5>Cart</h5>--%>
<%--                        </div>--%>
                        <div class="card-body cart">
                            <div class="col-sm-12 empty-cart-cls text-center"> <img src="https://i.imgur.com/dCdflKN.png" width="130" height="130" class="img-fluid mb-4 mr-3">
                                <h3><strong>Your Cart is Empty</strong></h3>
                                <h4>Add something to make me happy :)</h4> <a href="${pageContext.request.contextPath}/productList" class="btn btn-primary cart-btn-transform m-3" data-abc="true">continue shopping</a>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </c:if>
<%--    <c:if test="${empty isEmpty}">--%>
<%--        <form:form id="update-form" method="post" action="${pageContext.request.contextPath}/cart" commandName="productArrayDTO">--%>
<%--            <div id="vertical-products-cart">--%>
<%--                <c:forEach var="i" begin="0" end="${cart.cartItems.size() - 1}">--%>



<%--                    <div id="horizontal-cart">--%>
<%--                        <div><img id="image" src="${cart.cartItems.get(i).product.picturePath}"></div>--%>
<%--                        <div><c:out value="${cart.cartItems.get(i).product.productName}"/></div>--%>
<%--                        <div><c:out value="${cart.cartItems.get(i).product.price}"/>$</div>--%>
<%--                        <div>--%>
<%--                            <form:input path="productDTOItems[${i}].id" type="hidden"/>--%>
<%--                            <form:input path="productDTOItems[${i}].quantity"/>--%>
<%--                            <br>--%>
<%--                            <form:errors path="productDTOItems[${i}].quantity" cssClass="red-res"/>--%>
<%--                            <span class="green-res"><c:out value="${fn:contains(updatedProductIds, cart.cartItems.get(i).product.id) ? successfulUpdateMessage : ''}"/></span>--%>
<%--                        </div>--%>
<%--                        <div>--%>
<%--                            <button class="buttons" formmethod="post" formaction="${pageContext.request.contextPath}/cart/${cart.cartItems.get(i).product.id}">--%>
<%--                                Delete from cart--%>
<%--                            </button>--%>
<%--                        </div>--%>
<%--                    </div>--%>
<%--                </c:forEach>--%>
<%--            </div>--%>
<%--        </form:form>--%>

<%--    </c:if>--%>

    <c:if test="${empty isEmpty}">
        <div class="title">
            <div class="row">
                <div class="col align-self-center">
                    <h2><b>Shopping Cart</b></h2>
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
                <th></th>
            </tr>
            </thead>
            <tbody>
            <form:form id="update-form" method="post" action="${pageContext.request.contextPath}/cart" commandName="productArrayDTO">
                <c:forEach var="i" begin="0" end="${cart.cartItems.size() - 1}">
                    <tr>
                        <th scope="row">
                            <div class="product-thumb">
                                <a href="${pageContext.request.contextPath}/productDetails/${product.id}"><img class="img-fluid" src="${cart.cartItems.get(i).product.picturePath}" alt=""></a>
                                    <%--                                        <img class="img-fluid" src="${cart.cartItems.get(i).product.picturePath}">--%>
                            </div>
                        </th>
                        <td><c:out value="${cart.cartItems.get(i).product.productName}"/></td>
                        <td><c:out value="${cart.cartItems.get(i).product.price}"/>$</td>
                        <td>
                            <form:input path="productDTOItems[${i}].id" type="hidden"/>
                            <form:input path="productDTOItems[${i}].quantity"/>
                            <br>
                            <form:errors path="productDTOItems[${i}].quantity" cssClass="red-res"/>
                            <span class="green-res"><c:out value="${fn:contains(updatedProductIds, cart.cartItems.get(i).product.id) ? successfulUpdateMessage : ''}"/></span>
                        </td>
                        <td>
                            <button class="btn btn-warning" formmethod="post" formaction="${pageContext.request.contextPath}/cart/${cart.cartItems.get(i).product.id}">
                                <span class="close">&#10005;</span>
                            </button>
                        </td>
                    </tr>
                </c:forEach>
            </form:form>
            </tbody>
        </table>
        <div id="cart-buttons">
            <button class="btn btn-primary" id="update-cart" form="update-form">
                Update
            </button>
        </div>
        <form id="to-order-form" action="${pageContext.request.contextPath}/order?orderType=Delivery">
            <div class="items-inline">
                <label for="order-type">Choose order type:&nbsp</label><select name="orderType" id="order-type">
                <c:forEach items="${orderTypes}" var="orderType">
                    <option>${orderType.name()}</option>
                </c:forEach>
            </select>
                &nbsp
                <button class="btn btn-success" id="order-cart" form="to-order-form">
                    Order
                </button>
            </div>
        </form>
    </c:if>



    <tags:footer/>

    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta3/dist/js/bootstrap.bundle.min.js" integrity="sha384-JEW9xMcG8R+pH31jmWH6WWP0WintQrMb4s7ZOdauHnUtxwoG2vI5DkLtS3qm9Ekf" crossorigin="anonymous"></script>

</body>
</html>

<script type="text/javascript">
    document.addEventListener('DOMContentLoaded',function(){
        document.getElementById('order-type').onchange = function(){
            var value = this.value || "Delivery";
            document.forms[1].action='${pageContext.request.contextPath}/order?orderType=' + value;
        }
        document.forms[1].onsubmit = function(){
            if(this.action) return true;
            return false;
        }
    });
</script>
