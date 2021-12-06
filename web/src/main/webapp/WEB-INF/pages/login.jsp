<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="tags" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-eOJMYsd53ii+scO/bJGFsiCZc+5NDVN2yr8+0RDqr0Ql0h+rP48ckxlpbzKgwra6" crossorigin="anonymous">
    <link rel="preconnect" href="https://fonts.gstatic.com">
    <link href="https://fonts.googleapis.com/css2?family=Roboto:wght@300;400;500;700&display=swap" rel="stylesheet">
    <meta name="viewport" content="width=device-width, initial-scale=1">
<%--    <title>Log in</title>--%>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/styles/main.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/styles/auth.css">
<body>
    <tags:headerLogin/>
<%--    <div id="login-error">--%>
<%--        <c:if test="${not empty error}">--%>
<%--            ${error}--%>
<%--        </c:if>--%>
<%--    </div>--%>
<%--    <form name="form_login" action="<c:url value="/j_spring_security_check"/>" method="post">--%>
<%--        <table class="no-border-table">--%>
<%--            <tr>--%>
<%--                <td class="no-border-td">Email:</td>--%>
<%--                <td class="no-border-td">--%>
<%--                    <input type="text" name="username_login" value="">--%>
<%--                </td>--%>
<%--            </tr>--%>
<%--            <tr>--%>
<%--                <td class="no-border-td">Password:</td>--%>
<%--                <td class="no-border-td">--%>
<%--                    <input type="password" name="password_login" value="">--%>
<%--                </td>--%>
<%--            </tr>--%>
<%--            <tr>--%>
<%--                <td class="no-border-td">--%>
<%--                    <button class="buttons">--%>
<%--                        Submit--%>
<%--                    </button>--%>
<%--                </td>--%>
<%--            </tr>--%>
<%--        </table>--%>
<%--    </form>--%>
<%--    <div>--%>
<%--        <h3>--%>
<%--            Still don't have an existing account?--%>
<%--            <a href="${pageContext.request.contextPath}/registration">Create an account</a>--%>
<%--        </h3>--%>
<%--    </div>--%>

    <section class="forms-section">
        <h1 class="section-title">Sign in with email</h1>
        <div class="forms">
            <div class="form-wrapper is-active">
                <button type="button" class="switcher switcher-login">
                    Login
                    <span class="underline"></span>
                </button>
                <form class="form form-login" action="<c:url value="/j_spring_security_check"/>" method="post">
                    <fieldset>
                        <legend>Please, enter your email and password for login.</legend>
                        <div class="input-block">
                            <label for="login-email">E-mail</label>
                            <input id="login-email" type="email" required />
                        </div>
                        <div class="input-block">
                            <label for="login-password">Password</label>
                            <input id="login-password" type="password" required />
                        </div>
                    </fieldset>
                    <button type="submit" class="btn-login">Login</button>
                </form>
            </div>
            <div class="form-wrapper">
                <button type="button" class="switcher switcher-signup">
                    Sign Up
                    <span class="underline"></span>
                </button>
                <form class="form form-signup"method="post" action="${pageContext.request.contextPath}/registration" commandName="customUserDTO">
                    <fieldset>
                        <legend>
                            Please, enter your email, password and password confirmation for
                            sign up.
                        </legend>
                        <div class="input-block">
                            <label for="signup-email">First name</label>
                            <input id="signup-name" type="text" required />
                        </div>
                        <div class="input-block">
                            <label for="signup-email">Last name</label>
                            <input id="signup-surname" type="text" required />
                        </div>
                        <div class="input-block">
                            <label for="signup-email">E-mail</label>
                            <input id="signup-email" type="email" required />
                        </div>
                        <div class="input-block">
                            <label for="signup-password">Password</label>
                            <input id="signup-password" type="password" required />
                        </div>
                        <div class="input-block">
                            <label for="signup-password-confirm">Confirm password</label>
                            <input id="signup-password-confirm" type="password" required />
                        </div>
                    </fieldset>
                    <button type="submit" class="btn-signup">Continue</button>
                </form>
            </div>
        </div>
    </section>

    <tags:footer/>
    <script src="${pageContext.request.contextPath}/scripts/auth.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta3/dist/js/bootstrap.bundle.min.js" integrity="sha384-JEW9xMcG8R+pH31jmWH6WWP0WintQrMb4s7ZOdauHnUtxwoG2vI5DkLtS3qm9Ekf" crossorigin="anonymous"></script>
</body>
</html>
