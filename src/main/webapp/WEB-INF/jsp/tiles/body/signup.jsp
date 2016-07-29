<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>

<div class="signup">
	<div id="signup_form">
		<h3>Sign up</h3>
		<form action="signup" method="POST" enctype="UTF-8">
			<div class="reg_field_wrap">
				<label>First name: </label>
				<input type="text" name="firstName" id="firstName" />
			</div>
			<div class="reg_field_wrap">
				<label>Last name: </label>
				<input type="text" name="lastName" id="lastName" />
			</div>
			<div class="reg_field_wrap">
				<label>email: </label>
				<input type="text" name="email" id="email" />
			</div>
			<div class="reg_field_wrap">
				<label>Password: </label>
				<input id="password" name="password" type="password" />
			</div>
			<div class="reg_field_wrap">
				<label>Repeat password: </label>
				<input id="matchingPassword" name="matchingPassword" type="password" />
			</div>
			<input type="hidden"  name="${_csrf.parameterName}"   value="${_csrf.token}"/>
			<button type="submit">Sign up</button>
		</form>
		<div id="login_ref">
			<a href="login">Log in</a>
		</div>
	</div>
</div>