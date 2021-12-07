<%@ tag trimDirectiveWhitespaces="true" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ attribute name="cart" type="com.finewine.core.model.cart.Cart" required="true" %>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>


<%--<div class="container-fluid">--%>

<%--    <nav id="navbar"--%>
<%--         class="navbar fixed-top navbar-expand-md flex-nowrap navbar-new-top">--%>
<%--        <a href="${pageContext.request.contextPath}/productList" class="navbar-brand"><img--%>
<%--                src="images/logo.png" alt="logo" /></a>--%>
<%--        <ul class="nav navbar-nav mr-auto"></ul>--%>
<%--        <ul class="navbar-nav flex-row">--%>
<%--            <li class="nav-item">--%>
<%--                <security:authorize access="isAuthenticated()">--%>
<%--                    <span id="nickname-span"><security:authentication property="name"/></span>--%>
<%--                </security:authorize>--%>
<%--            </li>--%>
<%--            <li class="nav-item">--%>
<%--                <security:authorize access="isAuthenticated()">--%>
<%--                    <a id="logout" href="<c:url value="/j_spring_security_logout"/>">logout</a>--%>
<%--                </security:authorize>--%>
<%--            </li>--%>
<%--            <li>--%>
<%--                <security:authorize access="!isAuthenticated()">--%>
<%--                    <a id="login" href="${pageContext.request.contextPath}/login">login</a>--%>
<%--                </security:authorize>--%>
<%--            </li>--%>
<%--            <li>--%>
<%--                <a href="${pageContext.request.contextPath}/myAccount">My account</a>--%>
<%--            </li>--%>
<%--            <li class="nav-item">--%>
<%--                <a href="${pageContext.request.contextPath}/cart" class="nav-link px-2">--%>
<%--                    <img src="${pageContext.request.contextPath}/images/shopping-cart.png" alt="cart" />--%>
<%--                </a>--%>
<%--                <div class="items-inline">--%>
<%--                    <p id="cart-quantity"><c:out value="${cart.totalQuantity}"/></p>&nbsp--%>
<%--                    <p>items for</p>&nbsp--%>
<%--                    <p id="cart-cost"><c:out value="${cart.totalCost}"/></p>--%>
<%--                    <p>$</p>--%>
<%--                </div>--%>
<%--            </li>--%>
<%--        </ul>--%>
<%--    </nav>--%>
<%--    <br>--%>
<%--</div>--%>

<%--<div id="header-overall">--%>
<%--    <div id="header-title">--%>
<%--        <a href="${pageContext.request.contextPath}/productList"><h1>FineWine</h1></a>--%>
<%--    </div>--%>
<%--    <div id="header-right-block">--%>
<%--        <div id="header-auth">--%>
<%--            <security:authorize access="isAuthenticated()">--%>
<%--                <span id="nickname-span"><security:authentication property="name"/></span>--%>
<%--            </security:authorize>--%>
<%--            <security:authorize access="isAuthenticated()">--%>
<%--                <a id="logout" href="<c:url value="/j_spring_security_logout"/>">logout</a>--%>
<%--            </security:authorize>--%>
<%--            <security:authorize access="!isAuthenticated()">--%>
<%--                <a id="login" href="${pageContext.request.contextPath}/login">login</a>--%>
<%--            </security:authorize>--%>
<%--        </div>--%>
<%--        <div id="header-account-button">--%>
<%--            <a>My account</a>--%>
<%--        </div>--%>
<%--        <div id="header-cart-button">--%>
<%--            <div class="vertical-divs">--%>
<%--                <a href="${pageContext.request.contextPath}/cart">Cart</a>--%>
<%--                <div class="items-inline">--%>
<%--                    <p id="cart-quantity"><c:out value="${cart.totalQuantity}"/></p>&nbsp--%>
<%--                    <p>items for</p>&nbsp--%>
<%--                    <p id="cart-cost"><c:out value="${cart.totalCost}"/></p>--%>
<%--                    <p>$</p>--%>
<%--                </div>--%>
<%--            </div>--%>
<%--        </div>--%>
<%--    </div>--%>
<%--</div>--%>
<%--<hr/>--%>

<nav class="navbar navbar-expand-md navbar-light">
    <div class="container-fluid">
        <a class="navbar-brand" href="${pageContext.request.contextPath}/productList">
            <img src="images/logon.png" alt="FineWine" /></a>
        <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
            <span class="navbar-toggler-icon"></span>
        </button>
        <div class="collapse navbar-collapse" id="navbarSupportedContent">
            <ul class="navbar-nav me-auto mb-2 mb-lg-0 top-menu">
<%--                <li class="nav-item">--%>
<%--                    <a class="nav-link" href="#">Home</a>--%>
<%--                </li>--%>
                <li class="nav-item">
                    <a class="nav-link" href="${pageContext.request.contextPath}/productList" style="color: #a43300">Catalog</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="#" style="color: #a43300">News</a>
                </li>
            </ul>

            <ul class="navbar-nav ms-auto mb-2 mb-lg-0">
                <security:authorize access="isAuthenticated()">
                    <li class="nav-item">
                        <span class="navbar-brand"><security:authentication property="name"/></span>
                    </li>
                </security:authorize>
                <li class="nav-item dropdown">
                    <a class="nav-link dropdown-toggle" data-bs-toggle="dropdown" href="" alt="Account"><i class="far fa-user"></i></a>
                    <ul class="dropdown-menu" aria-labelledby="navbarDropdown">
                        <security:authorize access="!isAuthenticated()">
                            <li><a class="dropdown-item" href="${pageContext.request.contextPath}/registration">Sign up</a></li>
                            <li><a class="dropdown-item" href="${pageContext.request.contextPath}/login">Login</a></li>
                        </security:authorize>
                        <security:authorize access="isAuthenticated()">
                            <li>
                                <a class="dropdown-item" id="logout" href="<c:url value="/j_spring_security_logout"/>">Logout</a>
                            </li>
                            <li>
                                <a class="dropdown-item" href="${pageContext.request.contextPath}/myAccount">Profile
                                </a>
                            </li>
                        </security:authorize>
                    </ul>
                </li>

                <li class="nav-item">
                    <a class="nav-link" href="${pageContext.request.contextPath}/cart" >
                        <i class="fas fa-shopping-cart" alt="Cart"></i>
                    </a>
<%--                    <div class="modal fade" id="modal-cart" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">--%>
<%--                        <div class="modal-dialog modal-xl">--%>
<%--                            <div class="modal-content">--%>
<%--                                <div class="modal-header bg-secondary text-white">--%>
<%--                                    <h5 class="modal-title" id="exampleModalLabel">Корзина</h5>--%>
<%--                                    <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>--%>
<%--                                </div>--%>
<%--                                <div class="modal-body">--%>
<%--                                    <table class="table">--%>
<%--                                        <tbody>--%>
<%--                                        <tr>--%>
<%--                                            <td><img src="img/products/apple_cinema_30.jpg" alt=""></td>--%>
<%--                                            <td><a href="#">Apple Cinema 30"</a></td>--%>
<%--                                            <td>$230.99</td>--%>
<%--                                            <td>1</td>--%>
<%--                                        </tr>--%>
<%--                                        <tr>--%>
<%--                                            <td><img src="img/products/canon_eos_5d_1.jpg" alt=""></td>--%>
<%--                                            <td><a href="#">Canon EOS 5D</a></td>--%>
<%--                                            <td>$230.99</td>--%>
<%--                                            <td>1</td>--%>
<%--                                        </tr>--%>
<%--                                        <tr>--%>
<%--                                            <td><img src="img/products/hp_1.jpg" alt=""></td>--%>
<%--                                            <td><a href="#">HP</a></td>--%>
<%--                                            <td>$230.99</td>--%>
<%--                                            <td>1</td>--%>
<%--                                        </tr>--%>
<%--                                        </tbody>--%>
<%--                                    </table>--%>
<%--                                </div>--%>
<%--                                <div class="modal-footer">--%>
<%--                                    <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Close</button>--%>
<%--                                    <button type="button" class="btn btn-primary">Save changes</button>--%>
<%--                                </div>--%>
<%--                            </div>--%>
<%--                        </div>--%>
<%--                    </div>--%>
                </li>

<%--                <li class="nav-item">--%>
<%--                    <a class="nav-link" href="#"><i class="fas fa-search"></i></a>--%>
<%--                </li>--%>
            </ul>
        </div>
    </div>
</nav>