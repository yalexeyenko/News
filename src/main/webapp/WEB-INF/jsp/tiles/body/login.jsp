<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>

<div class="login">
	<div id="login_form">
		<h3>
			<spring:message code="login" />
		</h3>
		<form action="login" method="POST" enctype="UTF-8">
			<div class="login_field_wrap">
				<label>Email: </label> <input type="text" name="username"
					id="username" />
			</div>
			<div class="login_field_wrap">
				<label><spring:message code="Password" />: </label> <input
					name="password" type="password" id="password" />
			</div>
			<input type="hidden" name="${_csrf.parameterName}"
				value="${_csrf.token}" />
			<button type="submit">
				<spring:message code="login" />
			</button>
			<c:if test="${param.error != null}">
				<div id="login_error_message">
					<spring:message code="message.badCredentials">
					</spring:message>
				</div>
			</c:if>
		</form>
	</div>
</div>