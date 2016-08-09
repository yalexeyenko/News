<%@ page contentType="text/html;charset=UTF-8" language="java"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>

<div class="admin">
	<c:if test="${not empty page.content}">
<!-- 		put users table in here -->
	</c:if>
	<c:if test="${empty page.content}">
		<span><spring:message code="news.not.found"/></span>
	</c:if>
</div>