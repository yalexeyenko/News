<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<div class="viewNewsContent">
	<div class="view_news">
		<div id="view_wrap_title">
			<span class="tit"><spring:message code="content.view.title" />:</span>
			<span id="block">${newsDTO.title}</span>
		</div>
		<div id="view_wrap_status">
			<span class="tit"><spring:message code="news.status" />:</span>
			<span id="block"><spring:message code="${newsDTO.status}" /></span>
		</div>
		<div id="view_wrap_date">
			<span class="tit"><spring:message code="content.view.date" />:</span>
			<span id="block">${newsDTO.date}</span>
		</div>
		<div id="view_wrap_brief">
			<span class="tit"><spring:message code="content.view.brief" />:</span>
			<span id="block">${newsDTO.brief}</span>
		</div>
		<div id="view_wrap_content">
			<span class="tit"><spring:message code="content.view.content" />:</span>
			<span id="block">${newsDTO.content}</span>
		</div>
		<div id="edit_n_delete_ref">
			<c:if test="${newsDTO.status ne 'approved'}">
				<a href="showEditNews?id=${newsDTO.id}"> <spring:message
						code="content.view.button.edit" /></a>
			</c:if>
			<a href="deleteNews?id=${newsDTO.id}"> <spring:message
					code="content.view.button.delete" />
			</a>
		</div>
	</div>
</div>