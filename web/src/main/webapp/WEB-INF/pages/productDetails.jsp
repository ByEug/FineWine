<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="tags" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html>
<head>
    <link rel="stylesheet"
          href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.0/dist/css/bootstrap.min.css"
          integrity="sha384-B0vP5xmATw1+K9KRQjQERJvTumQW0nPEzvF6L/Z6nronJ3oUOFUFpCjEUQouq2+l"
          crossorigin="anonymous">
    <title>${product.productName}</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/styles/main.css">
</head>
<body>
    <tags:header cart="${cart}"/>
    <input class="buttons" type="button" onclick="history.go(-1);" value="Back to the products"/>
    <div id="success-result">
    </div>
    <div id="horizontal-pdp">
        <div id="vertical-pdp-left">
            <div class="items-inline">
                <p>Categories: </p>
                <c:forEach items="${product.categories}" var="category">
                    <p>&nbsp${category.categoryName} </p>
                </c:forEach>
            </div>
            <img id="image-pdp" src="${product.picturePath}">
            <div class="items-inline">
                <p>Year made: </p><p>&nbsp${product.yearMade}</p>
            </div>
            <div class="items-inline">
                <p>Country: </p><p>&nbsp${product.country.englishName}</p>
            </div>
            <div class="items-inline">
                <p id="description-pdp">${product.description}</p>
            </div>
        </div>
        <div id="vertical-pdp-right">
            <div class="items-inline">
                <p>Available in stock: </p><p>&nbsp${product.stock.stock}</p>
            </div>
            <div class="items-inline">
                <p>Price: </p><p>&nbsp${product.price}$</p>
            </div>
            <div id="add-to-cart-form">
                <form:form id="addToCartForm" method="post" modelAttribute="productDTO">
                    <input class="quantity-input" type="text" id="quantity" name="quantity" value="1"/>
                    <button class="buttons">
                        Add to Cart
                    </button>
                    <input id="productId" name="productId" type="hidden" value="${product.id}"/>
                    <div id="result-green">
                    </div>
                    <div id="result-red">
                    </div>
                </form:form>
            </div>
        </div>
    </div>
    <h3>Comments:</h3>
    <div class="vertical-divs">
        <c:forEach items="${comments}" var="comment">
            <div class="vertical-divs">
                <div class="name-and-data">
                    ${comment.user.username}
                </div>
                <div class="name-and-data">
                    ${comment.creatingDate.toString()}
                </div>
                <div>
                    ${comment.commentText}
                </div>
            </div>
            <br>
        </c:forEach>
    </div>
    <form:form method="post" action="${pageContext.request.contextPath}/productDetails/${product.id}" commandName="commentDTO">
        <div class="items-inline">
            <div>
                <form:textarea path="commentText" id="add-info-to-order" name="commentText" placeholder="Add a comment..." maxlength="2000"/>
            </div>
            <div>
                <button class="buttons">
                    Add a comment
                </button>
            </div>
        </div>
    </form:form>
    <br>
    <tags:footer/>
    <script src="http://code.jquery.com/jquery-1.8.3.js"></script>
    <script>
        jQuery(document).ready(function ($) {
            $("#addToCartForm").submit(function (event) {
                event.preventDefault();
                addToCart();
            });
        })

        function addToCart() {

            var id = $("#productId").val();
            var quantity = $("#quantity").val();

            $.ajax({
                type: 'POST',
                url: '${pageContext.request.contextPath}/ajaxCart',
                data: 'id=' + id + '&quantity=' + quantity,
                success: function (message) {
                    var json = JSON.stringify(message);
                    var jsonObject = JSON.parse(json);
                    $('#cart-quantity').text(jsonObject.totalQuantity);
                    $('#cart-cost').text(jsonObject.totalCost);
                    $('#success-result').text('Product added to cart successfully');
                    $('#result-green').text('Product added to cart successfully');
                    $('#result-red').text('');
                },
                error: function (message) {
                    $('#success-result').text('');
                    var json = JSON.stringify(message);
                    var jsonObject = JSON.parse(json);
                    var responseTextObject = JSON.parse(jsonObject.responseText);
                    $('#result-green').text('');
                    if (responseTextObject.errorsMessage !== undefined) {
                        $('#result-red').text(responseTextObject.errorsMessage);
                    } else {
                        $('#result-red').text(responseTextObject.errors[0].code);
                    }
                }
            });
        }
    </script>
</body>
</html>
