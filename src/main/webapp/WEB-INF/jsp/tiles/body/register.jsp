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
			<a href="login"><spring:message code="content.register.login" /></a>
		</div>
	</div>


<!-- //////////////////////// -->
	<div class="add_news_form">
		<form:form action="addNews" modelAttribute="newsDTO">
			<div id="input_wrap_title">
				<span><spring:message code="content.add.title" /></span>
				<form:input path="title" />
				<div id="title_errors"><form:errors path="title" /></div>
			</div>
			<div id="input_wrap_date">
				<span><spring:message code="content.add.date" /></span>
				<form:input path="date" />
				<div id="date_errors"><form:errors path="date" /></div>
			</div>
			<div id="input_wrap_brief">
				<span><spring:message code="content.add.brief" /></span>
				<form:textarea path="brief" />
				<div id="brief_errors"><form:errors path="brief" /></div>
			</div>
			<div id="input_wrap_content">
				<span><spring:message code="content.add.content" /></span>
				<form:textarea path="content" />
				<div id="content_errors">
					<form:errors path="content" />
				</div>
			</div>
			<div id="button_wrap">
				<form:button>
					<spring:message code="content.add.button.save" />
				</form:button>
				<a class="but_ref" href="listNews"> <spring:message
						code="content.add.button.cancel" />
				</a>
			</div>
		</form:form>
	</div>
</div>