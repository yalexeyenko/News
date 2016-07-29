<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>

<div class="login">
	<div id="login_form">
		<h3>Log in</h3>
		<form action="login" method="POST" enctype="UTF-8">
			<div class="login_field_wrap">
				<label>User name: </label>
				<input type="text" name="username" id="username" />
			</div>
			<div class="login_field_wrap">
				<label>Password: </label>
				<input name="password" type="password" id="password" />
			</div>
			<input type="hidden"  name="${_csrf.parameterName}"   value="${_csrf.token}"/>
			<button type="submit">Log in</button>
		</form>
		<div id="signup_ref">
			<a href="signup">Register</a>
		</div>
	</div>
</div>