<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>

<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<div class="addContent">
	<div class="add_news_form">
		<form:form action="addNews" modelAttribute="newsDTO">
			<div id="input_wrap_title">
				<span><spring:message code="content.add.title" /></span>
				<form:input path="title" />
				<div id="title_errors"><form:errors path="title" /></div>
			</div>
			<div id="input_wrap_status">
				<span><spring:message code="news.status" /></span>
				<form:select path="status">
					<form:option selected="true" value="draft"><spring:message code="draft" /></form:option>
					<form:option value="oncheck"><spring:message code="oncheck" /></form:option>
				</form:select>
			</div>
			<div id="input_wrap_date">
				<span><spring:message code="content.add.date" /></span>
				<form:input path="date" />
				<div id="date_errors"><form:errors path="date" /></div>
			</div>
			<div id="input_wrap_brief">
				<span><spring:message code="content.add.brief" /></span>
				<form:textarea path="brief" />
				<div id="brief_errors"><form:errors path="brief" /></div>
			</div>
			<div id="input_wrap_content">
				<span><spring:message code="content.add.content" /></span>
				<form:textarea path="content" />
				<div id="content_errors">
					<form:errors path="content" />
				</div>
			</div>
			<div id="button_wrap">
				<form:button>
					<spring:message code="content.add.button.save" />
				</form:button>
				<a class="but_ref" href="cabinet"> <spring:message
						code="content.add.button.cancel" />
				</a>
			</div>
		</form:form>
	</div>
</div>