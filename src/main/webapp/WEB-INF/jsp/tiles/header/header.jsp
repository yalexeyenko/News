<%@ page contentType="text/html;charset=UTF-8" language="java"%>

<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://www.springframework.org/security/tags"
	prefix="security"%>

<div class="header">
	<h1>
		<a href="showMainPage"><spring:message code="header.title" /></a>
	</h1>
	<security:authorize access="isAuthenticated()">
		<div id=userEmail>
			<span><security:authentication property="principal.username" /></span>
		</div>
	</security:authorize>
	<security:authorize access="isAnonymous()">
		<div id=userEmail>
			<span><spring:message code="guest" /></span>
		</div>
	</security:authorize>
	<div id="login_logout_ref">
		<security:authorize access="! isAuthenticated()">
			<a href="login">
				<spring:message code="login" />
			</a>|
			<a href="signup">
				<spring:message code="signup" />
			</a>
		</security:authorize>
		<security:authorize access="isAuthenticated()">
			<form:form action="logout" method="POST">
				<input type="hidden" name="${_csrf.parameterName}"
					value="${_csrf.token}" />
				<button>
					<spring:message code="logout" />
				</button>
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