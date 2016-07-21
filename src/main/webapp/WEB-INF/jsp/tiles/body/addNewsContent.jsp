<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>

<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<div class="addContent">
	<div class="add_news_form">
		<form:form action="addNews" modelAttribute="news">
			<div id="input_wrap_title">
				<span><spring:message code="content.add.title" /></span>
				<form:input path="title" />
				<label><form:errors path="title" /></label>
			</div>
			<div id="input_wrap_date">
				<span><spring:message code="content.add.date" /></span>
				<fmt:formatDate value="${news.date}" pattern="MM/dd/yyyy"
					var="formattedDate" />
				<form:input path="date" value="${formattedDate}" />
				<label><form:errors path="date" /></label>
			</div>
			<div id="input_wrap_brief">
				<span><spring:message code="content.add.brief" /></span>
				<form:textarea path="brief" />
				<label><form:errors path="brief" /></label>
			</div>
			<div id="input_wrap_content">
				<span><spring:message code="content.add.content" /></span>
				<form:textarea path="content" />
				<label><form:errors path="content" /></label>
			</div>
			<div id="button_wrap">
				<form:button>
					<spring:message code="content.add.button.save" />
				</form:button>
				<a class="but_ref" href="listNews"> <spring:message
						code="content.add.button.cancel" />
				</a>
			</div>
		</form:form>
	</div>
</div>