<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>

<div class="header">
	<h1>
		<spring:message code="header.title" />
	</h1>
	<div id="change_locale">
		<spring:url value="english">
			<span><spring:message code="header.lang.english" /></span>
		</spring:url>
		<spring:url value="russian">
			<span><spring:message code="header.lang.russian" /></span>
		</spring:url>


		<html:link page="/locale.do?method=english">
			<span><bean:message key="header.lang.english" /></span>
		</html:link>
		<html:link page="/locale.do?method=russian">
			<span><bean:message key="header.lang.russian" /></span>
		</html:link>
	</div>
</div>