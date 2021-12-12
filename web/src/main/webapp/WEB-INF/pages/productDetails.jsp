<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="tags" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html>
<head>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-eOJMYsd53ii+scO/bJGFsiCZc+5NDVN2yr8+0RDqr0Ql0h+rP48ckxlpbzKgwra6" crossorigin="anonymous">
    <link rel="preconnect" href="https://fonts.gstatic.com">
    <link href="https://fonts.googleapis.com/css2?family=Roboto:wght@300;400;500;700&display=swap" rel="stylesheet">
    <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.15.3/css/all.css" integrity="sha384-SZXxX4whJ79/gErwcOYf+zWLeJdY/qpuqC4cAa9rOGUstPomtqpuNWT9wdPEn2fk" crossorigin="anonymous">
    <title>${product.productName}</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/styles/main.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/styles/comment.css">
</head>
<body>
    <tags:header cart="${cart}"/>
    <div class="row">
        <div class="col-lg-12">
            <input class="btn btn-dark" type="button" onclick="history.go(-1);" value="<-"/>
        </div>
        <div id="horizontal-pdp">
            <div class="col-lg-6">
                <div class="product-card">
                    <div class="product-details">
                        <div class="items-inline">
                            <p>Categories:</p>
                            <c:forEach items="${product.categories}" var="category">
                                <p>&nbsp${category.categoryName} </p>
                            </c:forEach>
                        </div>
                    </div>
                    <div class="product-thumb">
                        <a href=""><img src="${product.picturePath}" alt=""></a>
                        <%--                    <img src="${product.picturePath}" alt="">--%>
                    </div>
                    <div class="product-details">
                        <div class="items-inline">
                            <p>Year made:</p><p>&nbsp${product.yearMade}</p>
                        </div>
                        <div class="items-inline">
                            <p>Country: </p><p>&nbsp${product.country.englishName}</p>
                        </div>
                        <p id="description-pdp">${product.description}</p>
                    </div>
                </div>

            </div>
            <div class="col-lg-4">
                <div class="product-card">
                    <div class="product-details">
                        <div class="items-inline">
                            <p>Available in stock: </p><p>&nbsp${product.stock.stock}</p>
                        </div>
                        <div></div>
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
            </div>
        </div>
    </div>


    <h3>Comments</h3>

    <section class="container">
        <div class="row">

            <div class="col-md-12">
                <div class="panel">
                    <div class="panel-body">
                        <form:form method="post"
                                   action="${pageContext.request.contextPath}/productDetails/${product.id}"
                                   commandName="commentDTO">
                            <div>
                                <form:textarea cssClass="form-control" rows="2" path="commentText" id="add-info-to-order" name="commentText"
                                               placeholder="Add a comment..." maxlength="2000"/>
                            </div>
                            <div class="mar-top clearfix">
                                <button class="btn btn-sm btn-primary pull-right" type="submit"><i
                                        class="fa fa-pencil fa-fw"></i> Добавить
                                </button>
                            </div>
                        </form:form>
                    </div>
                </div>
                <div class="panel">
                    <div class="panel-body">
                        <c:forEach items="${comments}" var="comment">
                            <!-- Комментарий -->
                            <div class="media-block">
                                <div class="media-body">
                                    <div class="mar-btm">
                                        <a href=""
                                           class="btn-link text-semibold media-heading box-inline">${comment.user.username}</a>
                                        <p class="text-muted text-sm"><i
                                                class="fa fa-lg"></i> ${comment.creatingDate.toString()}</p>
                                    </div>
                                    <p>${comment.commentText}</p>
                                    <hr>
                                </div>
                            </div>
                        </c:forEach>
                    </div>
                </div>
            </div>

        </div>
    </section>


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
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta3/dist/js/bootstrap.bundle.min.js" integrity="sha384-JEW9xMcG8R+pH31jmWH6WWP0WintQrMb4s7ZOdauHnUtxwoG2vI5DkLtS3qm9Ekf" crossorigin="anonymous"></script>
</body>
</html>
