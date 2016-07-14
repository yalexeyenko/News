<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>

<div class="addContent">
	<div class="add_news_form">
		<form:form action="addNews" modelAttribute="news">
			<div id="input_wrap_title">
				<span><spring:message code="content.add.title" /></span>
				<form:input path="title" />
			</div>
			<div id="input_wrap_date">
				<span><spring:message code="content.add.date" /></span>
				<form:input path="news.date" />
			</div>
			<div id="input_wrap_brief">
				<span><spring:message code="content.add.brief" /></span>
				<form:textarea path="brief" />
			</div>
			<div id="input_wrap_content">
				<span><spring:message code="content.add.content" /></span>
				<form:textarea path="content" />
			</div>
			<div id="button_wrap">
				<form:button>
					<spring:message code="content.add.button.save" />
				</form:button>
				<spring:url value="cancell">
					<form:button>
						<spring:message code="content.add.button.cancel" />
					</form:button>
				</spring:url>
			</div>
		</form:form>
	</div>
</div>