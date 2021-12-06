<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="tags" tagdir="/WEB-INF/tags" %>
<html>
<head>
    <title>Product list</title>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-eOJMYsd53ii+scO/bJGFsiCZc+5NDVN2yr8+0RDqr0Ql0h+rP48ckxlpbzKgwra6" crossorigin="anonymous">
    <link rel="preconnect" href="https://fonts.gstatic.com">
    <link href="https://fonts.googleapis.com/css2?family=Roboto:wght@300;400;500;700&display=swap" rel="stylesheet">
    <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.15.3/css/all.css" integrity="sha384-SZXxX4whJ79/gErwcOYf+zWLeJdY/qpuqC4cAa9rOGUstPomtqpuNWT9wdPEn2fk" crossorigin="anonymous">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/styles/main.css">
</head>
<body>
    <tags:header cart="${cart}"/>
<%--    <div class="container"></div>--%>
    <div class="container-fluid my-carousel">
        <div id="carouselExampleIndicators" class="carousel slide carousel-fade" data-bs-ride="carousel" data-bs-interval="5000">
            <div class="carousel-indicators">
                <button type="button" data-bs-target="#carouselExampleIndicators" data-bs-slide-to="0" class="active" aria-current="true" aria-label="Slide 1"></button>
                <button type="button" data-bs-target="#carouselExampleIndicators" data-bs-slide-to="1" aria-label="Slide 2"></button>
                <button type="button" data-bs-target="#carouselExampleIndicators" data-bs-slide-to="2" aria-label="Slide 3"></button>
            </div>
            <div class="carousel-inner">
                <div class="carousel-item active">
                    <img src="images/spirit1.jpeg" class="d-block w-100" alt="...">
                </div>
                <div class="carousel-item">
                    <img src="images/spirit2.jpg" class="d-block w-100" alt="...">
                </div>
                <div class="carousel-item">
                    <img src="images/spirit3.jpeg" class="d-block w-100" alt="...">
                </div>
            </div>
            <button class="carousel-control-prev" type="button" data-bs-target="#carouselExampleIndicators" data-bs-slide="prev">
                <span class="carousel-control-prev-icon" aria-hidden="true"></span>
                <span class="visually-hidden">Previous</span>
            </button>
            <button class="carousel-control-next" type="button" data-bs-target="#carouselExampleIndicators" data-bs-slide="next">
                <span class="carousel-control-next-icon" aria-hidden="true"></span>
                <span class="visually-hidden">Next</span>
            </button>
        </div>
    </div>

    <section class="main-content">
        <div class="container">
            <section class="row">
                <c:forEach var="product" items="${products}">
                <div class="col-lg-4 col-sm-6 mb-3">
                    <div class="product-card">
                        <div class="product-thumb">
                            <a href="#"><img src="${product.picturePath}" alt=""></a>
                        </div>
                        <div class="product-details">
                            <h4><a href="${pageContext.request.contextPath}/productDetails/${product.id}">${product.productName}</a></h4>
                            <p>${product.country.englishName}</p>
                            <p>${product.creatingDate}</p>
                            <p>Stock: ${product.stock.stock}</p>
                            <div>
                                <span>
                                    <c:forEach var="category" items="${product.categories}">
                                        <p>${category.categoryName}</p>
                                    </c:forEach>
                                </span>
                            </div>
                            <div class="product-bottom-details d-flex justify-content-between">
                                <div class="product-price">
<%--                                    <small>$230.99</small>--%>
                                    ${product.price}$
                                </div>
                                <div class="product-links">
                                    <a href=""><i class="fas fa-shopping-cart"></i></a>
<%--                                    <a href="#"><i class="far fa-heart"></i></a>--%>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
                </c:forEach>
            </section>
        </div>
    </section>
<%--                <div class="row">--%>
<%--            <c:forEach var="product" items="${products}">--%>
<%--                <div class="col-md-4">--%>
<%--                    <div>--%>
<%--                        <img id="image" src="${product.picturePath}">--%>
<%--                        <a href="${pageContext.request.contextPath}/productDetails/${product.id}"><h3>${product.productName}</h3></a>--%>
<%--                        <p>${product.country.englishName}</p>--%>
<%--                        <p>${product.creatingDate}</p>--%>
<%--                        <p>Price: ${product.price}$</p>--%>
<%--                        <p>Stock: ${product.stock.stock}</p>--%>
<%--                    </div>--%>
<%--                    <div>--%>
<%--                        <span>--%>
<%--                            <c:forEach var="category" items="${product.categories}">--%>
<%--                                <p>${category.categoryName}</p>--%>
<%--                            </c:forEach>--%>
<%--                        </span>--%>
<%--                    </div>--%>
<%--                </div>--%>

<%--            </c:forEach>--%>
<%--        </div>--%>
<%--    </div>--%>

    <tags:footer/>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta3/dist/js/bootstrap.bundle.min.js" integrity="sha384-JEW9xMcG8R+pH31jmWH6WWP0WintQrMb4s7ZOdauHnUtxwoG2vI5DkLtS3qm9Ekf" crossorigin="anonymous"></script>
</body>
</html>
