<%@ page contentType="text/html;charset=UTF-8" language="java"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>

<div class="admin">
	<c:if test="${not empty page.content}">
		<div id="history_table">
			<table>
				<tr>
					<th>№</th>
					<th>Author</th>
					<th>Date</th>
					<th>Title</th>
					<th>Brief description</th>
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
		<div id="paging">
			<c:if test="${page.number > 0}">
				<a href="showHistory?pageNumber=${page.number - 1}">&lt;</a>
			</c:if>
			<label>${page.number + 1}</label>
			<c:if test="${page.totalPages > page.number + 1}">
				<a href="showHistory?pageNumber=${page.number + 1}">&gt;</a>
			</c:if>
		</div>
	</c:if>
	<c:if test="${empty page.content}">
		<span>History is empty</span>
	</c:if>	
</div>