<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>

<div class="registration">
	<div id="register_form">
		<h3><spring:message code="content.register.title"/></h3>
		<form:form action="register" modelAttribute="userDTO" method="POST" enctype="UTF-8">
			<div class="reg_field_wrap">
				<label><spring:message code="user.firstName"/></label>
				<form:input path="firstName" />
				<form:errors path="firstName" element="div" />
			</div>
			<div class="reg_field_wrap">
				<label><spring:message code="user.lastName"/></label>
				<form:input path="lastName" />
				<form:errors path="lastName" element="div" />
			</div>
			<div class="reg_field_wrap">
				<label><spring:message code="user.email"/></label>
				<form:input path="email" />
				<form:errors path="email" element="div" />
			</div>
			<div class="reg_field_wrap">
				<label><spring:message code="user.password"/></label>
				<form:input path="password" type="password" />
				<form:errors path="password" element="div" />
			</div>
			<div class="reg_field_wrap">
				<label><spring:message code="user.confirm.password"/></label>
				<form:input path="matchingPassword" type="password" />
				<form:errors element="div" />
			</div>
			<button type="submit"><spring:message code="content.register.button"/></button>
		</form:form>
		<div id="login_ref">
			<a href="showLoginForm"><spring:message code="content.register.login.ref" /></a>
		</div>
	</div>
</div>