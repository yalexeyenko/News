<%@ page contentType="text/html;charset=UTF-8" language="java"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>

<div class="listNewsContent">
	<form:form action="listNews" modelAttribute="idList">
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
					<spring:url value="showViewNews">
						<spring:message code="content.list.item.ref.view"/>
						<spring:param name="id" value="${newsItem.id}"/>
					</spring:url>
					<spring:url value="showEditNews">
						<spring:message code="content.list.item.ref.edit"/>
						<spring:param name="id" value="${newsItem.id}"/>
					</spring:url>
				

					<html:multibox property="itemsToDelete" value="${news.id}" />
				</div>
			</div>
		</c:forEach>
		<div class="multi_delete">
			<html:submit>
				<bean:message key="content.list.button.delete" />
			</html:submit>
		</div>
	</form:form>
</div>