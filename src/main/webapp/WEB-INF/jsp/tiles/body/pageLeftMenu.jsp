<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>

<div class="left_menu">
	<h4>
		<spring:message code="left.menu.title.news" />
	</h4>
	<div class="left_menu_items_block">
		<ul>
			<li><a href="listNews">
					<spring:message code="left.menu.ref.news.list" />
				</a></li>
			<li><a href="showAddNews">
					<spring:message code="left.menu.ref.add.news" />
				</a></li>
		</ul>
	</div>
</div>