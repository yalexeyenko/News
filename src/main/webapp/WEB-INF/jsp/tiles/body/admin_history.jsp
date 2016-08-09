<%@ page contentType="text/html;charset=UTF-8" language="java"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<div class="admin">
		<div id="date_filter_form">
			<form action="showHistoryFilteredByDate">
				<label><spring:message code="from" /> </label><input type="date" value="${startDate}" name="startDate" /> 
				<label><spring:message code="to" /> </label><input type="date" value="${endDate}" name="endDate" />
				<div id="date_filter_buttons">
					<button>
						<spring:message code="apply" />
					</button>
					<a class="but_ref" href="showHistory"> <spring:message
							code="refresh" />
					</a>
				</div>
			</form>
		</div>
	<c:if test="${not empty page.content}">
		<tiles:insertAttribute name="historyTable" />
		<div id="paging">
			<c:if test="${page.number > 0}">
				<a href="showHistoryFilteredByDate?pageNumber=${page.number - 1}&startDate=${startDate}&endDate=${endDate}">&lt;</a>
			</c:if>
			<label>${page.number + 1}</label>
			<c:if test="${page.totalPages > page.number + 1}">
				<a href="showHistoryFilteredByDate?pageNumber=${page.number + 1}&startDate=${startDate}&endDate=${endDate}">&gt;</a>
			</c:if>
		</div>
	</c:if>
	<c:if test="${empty page.content}">
		<span><spring:message code="history.empty" /></span>
	</c:if>
</div>