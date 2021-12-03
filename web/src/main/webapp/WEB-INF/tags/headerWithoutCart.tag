<%@ tag trimDirectiveWhitespaces="true" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>

<div id="header-overall">
    <div id="header-title">
        <a href="${pageContext.request.contextPath}/productList"><h1>FineWine</h1></a>
    </div>
    <div id="header-right-block">
        <div id="header-auth">
            <security:authorize access="isAuthenticated()">
                <span id="nickname-span"><security:authentication property="name"/></span>
            </security:authorize>
            <security:authorize access="isAuthenticated()">
                <a id="logout" href="<c:url value="/j_spring_security_logout"/>">logout</a>
            </security:authorize>
            <security:authorize access="!isAuthenticated()">
                <a id="login" href="${pageContext.request.contextPath}/login">login</a>
            </security:authorize>
        </div>
        <div id="header-account-button">
            <a>My account</a>
        </div>
    </div>
</div>
<hr/>
