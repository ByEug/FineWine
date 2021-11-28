<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="tags" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html>
<head>
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
</body>

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

</html>
