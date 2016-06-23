<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib uri="http://struts.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean"%>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic"%>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html"%>

<div class="header">
	<h1><bean:message key="header.title" /> </h1>
	<div id="change_locale">
		<html:link page="/locale.do?method=english">
			<bean:message key="header.lang.english" /> 
		</html:link>
		<html:link page="/locale.do?method=russian">
			<bean:message key="header.lang.russian" />
		</html:link>
	</div>	
</div>