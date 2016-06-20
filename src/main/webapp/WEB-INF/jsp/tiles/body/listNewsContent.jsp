<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib uri="http://struts.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean"%>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic"%>

<div class="listNewsContent">
	list news
	<html:form action="/news" name="newsForm">
		<logic:iterate name="newsForm" property="newsList"  id="news">
			<span><bean:write name="news" property="title" /></span>
			<span><bean:write name="news" property="content" /></span>
		</logic:iterate>
	</html:form>
</div>