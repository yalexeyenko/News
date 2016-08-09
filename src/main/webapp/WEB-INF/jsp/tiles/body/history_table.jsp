<%@ page contentType="text/html;charset=UTF-8" language="java"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>

<div id="history_table">
	<table>
		<tr>
			<th>№</th>
			<th><spring:message code="author" /></th>
			<th><spring:message code="date" /></th>
			<th><spring:message code="title" /></th>
			<th><spring:message code="brief" /></th>
		</tr>
		<c:forEach items="${page.content}" var="newsItem" varStatus="status">
			<tr>
				<td>${status.count}</td>
				<td>${newsItem.userDTO.firstName}${newsItem.userDTO.lastName}</td>
				<td>${newsItem.date}</td>
				<td>${newsItem.title}</td>
				<td>${newsItem.brief}</td>
			</tr>
		</c:forEach>
	</table>
</div>
