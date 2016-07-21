<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>

<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<div class="editContent">
	<div class="edit_news_form">
		<form:form action="editNews" modelAttribute="news">
			<div id="input_wrap_title">
				<span id="tit"><spring:message code="content.add.title" />:</span>
				<form:input path="title" />
			</div>
			<div id="input_wrap_date">
				<span id="tit"><spring:message code="content.add.date" />:</span>
				<fmt:formatDate value="${news.date}" pattern="MM/dd/yyyy"
					var="formattedDate" />
				<form:input path="date" value="${formattedDate}" />
			</div>
			<div id="input_wrap_brief">
				<span id="tit"><spring:message code="content.add.brief" />:</span>
				<form:textarea path="brief" />
			</div>
			<div id="input_wrap_content">
				<span id="tit"><spring:message code="content.add.content" />:</span>
				<form:textarea path="content" />
			</div>
			<div id="button_wrap">
				<form:button>
					<spring:message code="content.add.button.save" />
					<input type="hidden" name="id" value="${news.id}" />
				</form:button>
				<a class="but_ref" href="listNews"> <spring:message
						code="content.add.button.cancel" />
				</a>
			</div>
		</form:form>
	</div>
</div>