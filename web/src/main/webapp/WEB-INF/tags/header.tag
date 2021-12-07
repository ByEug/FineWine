<%@ tag trimDirectiveWhitespaces="true" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ attribute name="cart" type="com.finewine.core.model.cart.Cart" required="true" %>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>


<nav class="navbar navbar-expand-md navbar-light">
    <div class="container-fluid">
        <a class="navbar-brand" href="${pageContext.request.contextPath}/productList">
            <img src="images/logon.png" alt="FineWine" /></a>
        <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
            <span class="navbar-toggler-icon"></span>
        </button>
        <div class="collapse navbar-collapse" id="navbarSupportedContent">
            <ul class="navbar-nav me-auto mb-2 mb-lg-0 top-menu">
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
                        <c:if test="${empty isEmpty}">
                            <li class="nav-item">
                                <div class="items-inline">
                                    <p id="cart-quantity"><c:out value="${cart.totalQuantity}"/></p>&nbsp
                                    <p>items for</p>&nbsp
                                    <p id="cart-cost"><c:out value="${cart.totalCost}"/>$</p>
                                </div>
                            </li>
                        </c:if>
                    </a>
                </li>
            </ul>
        </div>
    </div>
</nav>