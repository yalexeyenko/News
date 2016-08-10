<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>

<div class="signup">
	<div id="signup_form">
		<h3><spring:message code="signup" /></h3>
		<form:form action="register" modelAttribute="userDTO" method="POST" enctype="UTF-8">
			<div class="reg_field_wrap">
				<label><spring:message code="firstName" />: </label>
				<form:input path="firstName" value="" />
				<form:errors path="firstName" element="div" class="input_error" />
			</div>
			<div class="reg_field_wrap">
				<label><spring:message code="lastName" />: </label>
				<form:input path="lastName" value="" />
				<form:errors path="lastName" element="div" class="input_error"  />
			</div>
			<div class="reg_field_wrap">
				<label><spring:message code="email" />: </label>
				<form:input path="email" value="" />
				<form:errors path="email" element="div" class="input_error" />
			</div>
			<div class="reg_field_wrap">
				<label><spring:message code="Password" />: </label>
				<form:input path="password" value="" type="password" />
				<form:errors path="password" element="div" class="input_error" />
			</div>
			<div class="reg_field_wrap">
				<label><spring:message code="repeatPassword" />: </label>
				<form:input path="matchingPassword" value="" type="password" />
				<form:errors element="div" class="input_error" />
			</div>
			<input type="hidden" name="${_csrf.parameterName}"
				value="${_csrf.token}" />
			<button type="submit"><spring:message code="signup" /></button>
		</form:form>
	</div>
</div>