<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="tags" tagdir="/WEB-INF/tags" %>
<html>
<head>
    <title>Product list</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/styles/main.css">
</head>
<body>
    <tags:header/>
    <c:forEach var="product" items="${products}">
        <div>
            <img id="image" src="${product.picturePath}">
            <a href="${pageContext.request.contextPath}/productDetails/${product.id}"><h3>${product.productName}</h3></a>
            <p>${product.country.englishName}</p>
            <p>${product.creatingDate}</p>
            <p>Price: ${product.price}$</p>
            <p>Stock: ${product.stock.stock}</p>
        </div>
        <div>
            <span>
                <c:forEach var="category" items="${product.categories}">
                    <p>${category.categoryName}</p>
                </c:forEach>
            </span>
        </div>
    </c:forEach>
<%--    <tags:footer/>--%>
</body>
</html>
