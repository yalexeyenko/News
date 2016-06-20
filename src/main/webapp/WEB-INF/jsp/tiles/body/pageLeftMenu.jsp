<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib uri="http://struts.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean"%>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic"%>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html"%>

<div class="left_menu">
	<h4>News</h4>
	<div class="left_menu_items_block">
		<ul>
			<li><html:link action="/news?method=listNews" >News List</html:link></li>
			<li><html:link action="/news?method=showAddNews" >Add News</html:link></li>
		</ul>
	</div>
</div>