<%@ page contentType="text/html;charset=UTF-8" language="java"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>

<div class="listNewsContent">
	<form:form action="deleteNewsList" modelAttribute="newsCheckbox">
		<c:forEach items="${newsList}" var="newsItem">
			<div class="news_item_block">
				<div class="title_date">
					<div class="title">
						<span>${newsItem.title}</span>
					</div>
					<div class="date">
						<span>${newsItem.date}</span>
					</div>
				</div>
				<div class="news_brief">
					<span>${newsItem.brief}</span>
				</div>
				<div class="view_n_edit_ref">
					<a href="showViewNews?id=${newsItem.id}">
						<spring:message code="content.list.item.ref.view"/>
					</a>
					<a href="showEditNews?id=${newsItem.id}">
						<spring:message code="content.list.item.ref.edit"/>
					</a>
					<form:checkbox path="idList" value="${newsItem.id}" />
				</div>
			</div>
		</c:forEach>
		<div class="multi_delete">
			<form:button><spring:message code="content.list.button.delete"/></form:button>
		</div>
	</form:form>
</div>