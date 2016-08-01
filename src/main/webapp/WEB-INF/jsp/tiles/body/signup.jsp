<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>

<div class="signup">
	<div id="signup_form">
		<h3>Sign up</h3>
		<form:form action="register" modelAttribute="userDTO" method="POST" enctype="UTF-8">
			<div class="reg_field_wrap">
				<label>First name: </label>
				<form:input path="firstName" value="" />
				<form:errors path="firstName" element="div" />
			</div>
			<div class="reg_field_wrap">
				<label>Last name: </label>
				<form:input path="lastName" value="" />
				<form:errors path="lastName" element="div"  />
			</div>
			<div class="reg_field_wrap">
				<label>email: </label>
				<form:input path="email" value="" />
				<form:errors path="email" element="div" />
			</div>
			<div class="reg_field_wrap">
				<label>Password: </label>
				<form:input path="password" value="" type="password" />
				<form:errors path="password" element="div" />
			</div>
			<div class="reg_field_wrap">
				<label>Repeat password: </label>
				<form:input path="matchingPassword" value="" type="password" />
				<form:errors element="div" />
			</div>
			<input type="hidden" name="${_csrf.parameterName}"
				value="${_csrf.token}" />
			<button type="submit">Sign up</button>
		</form:form>
		<div id="login_ref">
			<a href="login">Log in</a>
		</div>
	</div>
</div>