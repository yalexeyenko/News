<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>

<div class="login">
	<div id="login_form">
		<h3>Log in</h3>
		<form action="login" method="POST" enctype="UTF-8">
			<div class="login_field_wrap">
				<label>Email: </label>
				<input type="text" name="username" />
			</div>
			<div class="login_field_wrap">
				<label>Password: </label>
				<input name="password" type="password" />
			</div>
			<input type="hidden"  name="${_csrf.parameterName}"   value="${_csrf.token}"/>
			<button type="submit">Log in</button>
		</form>
		<div id="signup_ref">
			<a href="signup">Sign up</a>
		</div>
	</div>
</div>