<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="tags" tagdir="/WEB-INF/tags" %>
<html>
<head>
    <title>${product.productName}</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/styles/main.css">
</head>
<body>
<tags:header/>
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
        </div>
    </div>
</body>
</html>
