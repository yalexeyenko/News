<%@ page contentType="text/html;charset=UTF-8" language="java"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>

<spring:url value="/img" var="img" />

<div class="users">
	<c:if test="${not empty page.content}">
		<div id="table_users">
			<table>
				<tr>
					<th>№</th>
					<th><spring:message code="user" /></th>
					<th><spring:message code="email" /></th>
					<th><spring:message code="status" /></th>
				</tr>
				<c:forEach items="${page.content}" var="userDTO" varStatus="status">
					<tr>
						<td>${status.count}</td>
						<td>${userDTO.firstName}${userDTO.lastName}</td>
						<td>${userDTO.email}</td>
						<td>
							<c:if test="${userDTO.enabled eq true}">
								<a id="enabled_user" href="disableUser?id=${userDTO.id}"><img src="${img}/padlock_opened.png"></img></a>
							</c:if>
							<c:if test="${userDTO.enabled eq false}">
								<a id="disabled_user" href="enableUser?id=${userDTO.id}"><img src="${img}/padlock_closed.png"></img></a>
							</c:if>
						</td>
					</tr>
				</c:forEach>
			</table>
			<div id="paging">
				<c:if test="${page.number > 0}">
					<a href="cabinet?pageNumber=${page.number - 1}">&lt;</a>
				</c:if>
				<label>${page.number + 1}</label>
				<c:if test="${page.totalPages > page.number + 1}">
					<a href="cabinet?pageNumber=${page.number + 1}">&gt;</a>
				</c:if>
			</div>
		</div>
	</c:if>
	<c:if test="${empty page.content}">
		<span><spring:message code="users.not.found" /></span>
	</c:if>
</div>