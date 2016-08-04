<%@ page contentType="text/html;charset=UTF-8" language="java"%>

<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<div class="viewNewsContent">
	<div class="view_news">
		<form:form action="moderate" modelAttribute="newsDTO">
			<div id="view_wrap_title">
				<span class="tit"><spring:message code="content.view.title" />:</span>
				<span id="block">${newsDTO.title}</span>
			</div>
			<div id="view_wrap_author">
				<span class="tit"><spring:message code="author" />:</span> <span
					id="block">${newsDTO.userDTO.firstName}
					${newsDTO.userDTO.lastName}</span>
			</div>
			<div id="input_wrap_status">
				<span><spring:message code="news.status" /></span>
				<form:select path="status">
					<form:option selected="true" value="oncheck">
						<spring:message code="oncheck" />
					</form:option>
					<form:option value="approved">
						<spring:message code="approved" />
					</form:option>
					<form:option value="fix">
						<spring:message code="fix" />
					</form:option>
				</form:select>
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
			<div id="button_wrap">
				<form:button>
					<spring:message code="content.add.button.save" />
					<input type="hidden" name="id" value="${newsDTO.id}" />
					<input type="hidden" name="email" value="${newsDTO.userDTO.email}" />
				</form:button>
				<a class="but_ref" href="admin"> <spring:message
						code="content.add.button.cancel" />
				</a>
			</div>
		</form:form>
	</div>
</div>