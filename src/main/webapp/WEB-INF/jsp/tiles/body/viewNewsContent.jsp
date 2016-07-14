<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>

<div class="viewNewsContent">
	<div class="view_news">
		<div id="view_wrap_title">
			<span id="tit"><spring:message code="content.view.title" /></span> <span
				id="block">${news.title}</span>
		</div>
		<div id="view_wrap_date">
			<span id="tit"><spring:message code="content.view.date" /></span> <span
				id="block">${news.date}</span>
		</div>
		<div id="view_wrap_brief">
			<span id="tit"><spring:message code="content.view.brief" /></span> <span
				id="block">${news.brief}</span>
		</div>
		<div id="view_wrap_content">
			<span id="tit"><spring:message code="content.view.content" /></span>
			<span id="block">${news.content}</span>
		</div>
		<div id="edit_n_delete_ref">
			<spring:url value="showEditNews">
				<spring:message code="content.view.button.edit" />
				<spring:param name="id" value="${news.id}" />
			</spring:url>
			<spring:url value="deleteNews">
				<spring:message code="content.view.button.delete" />
				<spring:param name="id" value="${news.id}" />
			</spring:url>
		</div>
	</div>
</div>