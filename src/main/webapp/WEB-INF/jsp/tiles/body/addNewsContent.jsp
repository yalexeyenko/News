<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib uri="http://struts.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean"%>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic"%>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<div class="addContent">
	<div class="add_news_form">
		<html:form action="/news?method=addNews">
			<div id="input_wrap_title">
				<span><bean:message key="content.add.title" /></span>
				<html:text name="newsForm" property="newsTitle" value="" />
			</div>
			<div id="input_wrap_date">
				<span><bean:message key="content.add.date" /></span>
				<html:text name="newsForm" property="date"
					value="${newsForm.date}" />
			</div>
			<div id="input_wrap_brief">
				<span><bean:message key="content.add.brief" /></span>
				<html:textarea name="newsForm" property="brief" value=""/>
			</div>
			<div id="input_wrap_content">
				<span><bean:message key="content.add.content" /></span>
				<html:textarea name="newsForm" property="content" value="" />
			</div>
			<div id="button_wrap">
				<html:submit>
					<bean:message key="content.add.button.save" />
				</html:submit>
				<html:link action="/news?method=listNews">
					<button type="button">
						<bean:message key="content.add.button.cancel" />
					</button>
				</html:link>
			</div>
		</html:form>
	</div>
</div>