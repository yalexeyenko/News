<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>

<div class="header">
	<h1>
		<spring:message code="header.title" />
	</h1>
	<div id="change_locale">
		<a href="?locale=en" >
			<span><spring:message code="header.lang.english" /></span>
		</a>
		<a href="?locale=ru">
			<span><spring:message code="header.lang.russian" /></span>
		</a>
	</div>
</div>