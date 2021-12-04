<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="tags" tagdir="/WEB-INF/tags" %>
<html>
<head>
    <title>Product list</title>
    <link href="https://fonts.googleapis.com/css?family=Poppins:300,400,500,600,700,800,900" rel="stylesheet">
    <link rel="stylesheet" href="css/A.ionicons.min.css+style.css,Mcc.bnVW-mDmyB.css.pagespeed.cf.imjSJubmRP.css">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <script
            src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
    <script
            src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
    <script
            src="//maxcdn.bootstrapcdn.com/bootstrap/4.1.1/js/bootstrap.min.js"></script>
    <script
            src="//cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
    <link
            href="//maxcdn.bootstrapcdn.com/bootstrap/4.1.1/css/bootstrap.min.css"
            rel="stylesheet" id="bootstrap-css">

    <link rel="stylesheet"
          href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">

    <script
            src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
    <script
            src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/styles/main.css">
</head>
<body>
    <tags:header cart="${cart}"/>
    <div class="container"></div>
        <div class="row">
            <c:forEach var="product" items="${products}">
                <div class="col-md-4">
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
                </div>

            </c:forEach>
        </div>
    </div>

    <tags:footer/>
</body>
</html>
