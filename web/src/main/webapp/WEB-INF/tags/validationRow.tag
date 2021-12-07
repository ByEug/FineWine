<%@ tag trimDirectiveWhitespaces="true" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ attribute name="label" required="true" %>
<%@ attribute name="id" required="true" %>
<%@ attribute name="name" required="true" %>

<%--<div class="items-inline">--%>
    <div class="label-field">
        <label  for="${id}"><spring:theme code="${label}"/></label><span class="required" style="color: darkred">*</span>
    </div>
    <div class="input-field">
        <form:input id="${id}" path="${name}"/>
        <br>
        <form:errors path="${name}" cssClass="red-res"/>
    </div>
<%--</div>--%>