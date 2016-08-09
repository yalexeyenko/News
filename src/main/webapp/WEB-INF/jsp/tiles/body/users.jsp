<%@ page contentType="text/html;charset=UTF-8" language="java"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>

<div class="users">
	<c:if test="${not empty page.content}">
		<div id="table_users">
			<tr>
				<th>№</th>
				<th><spring:message code="user" /></th>
				<th><spring:message code="email" /></th>
				<th><spring:message code="status" /></th>
			</tr>
			<c:forEach items="${page.content}" var="userDTO" varStatus="status">
				<tr>
					<td>${status.count}</td>
					<td>${userDTO.firstName} ${userDTO.lastName}</td>
					<td>${userDTO.email}</td>
					<td>
						<c:if test="${userDTO.enabled eq 'true'}">
							
						</c:if>
					</td>
				</tr>
			</c:forEach>
		</div>
	</c:if>
	<c:if test="${empty page.content}">
		<span><spring:message code="users.not.found" /></span>
	</c:if>
</div>