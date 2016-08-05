<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>

<div class="error403">
	<h1>HTTP Status 403 - <spring:message code="accessDenied"/></h1>
	<h3>${currentUser} <spring:message code="haveNoPermission"/> <a href="showMainPage"><spring:message code="goHome"/></a>!</h3>
</div>