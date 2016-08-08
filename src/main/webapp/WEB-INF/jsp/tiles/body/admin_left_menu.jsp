<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>

<div class="left_menu">
	<h4>
		<spring:message code="left.menu.title.news" />
	</h4>
	<div class="left_menu_items_block">
		<ul>
			<li><a href="admin"> <spring:message
						code="left.menu.ref.home" />
			</a></li>
			<li><a href="showHistory"> <spring:message
						code="left.menu.history" />
			</a></li>
			<li>
		</ul>
	</div>
</div>