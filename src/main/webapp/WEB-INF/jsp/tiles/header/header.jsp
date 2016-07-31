<%@ page contentType="text/html;charset=UTF-8" language="java"%>

<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://www.springframework.org/security/tags"
	prefix="security"%>

<div class="header">
	<h1>
		<spring:message code="header.title" />
	</h1>
	<div id="login_ref">
		<security:authorize access="! isAuthenticated()">
			<a href="login">Log in</a>
			<a href="signup">Sign up</a>
		</security:authorize>
		<security:authorize access="isAuthenticated()">
			<form:form action="logout" method="POST">
				<input type="hidden" name="${_csrf.parameterName}"
					value="${_csrf.token}" />
				<input type="submit" value="Log out" />
			</form:form>
		</security:authorize>

	</div>
	<div id="change_locale">
		<a href="?locale=en"> <span><spring:message
					code="header.lang.english" /></span>
		</a> <a href="?locale=ru"> <span><spring:message
					code="header.lang.russian" /></span>
		</a>
	</div>
</div>