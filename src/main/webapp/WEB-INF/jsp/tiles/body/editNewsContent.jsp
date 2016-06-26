<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib uri="http://struts.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean"%>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic"%>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html"%>

<div class="editContent">
	<div class="edit_news_form">
		<html:form action="/news?method=editNews">
			<div id="input_wrap_title">
				<span><bean:message key="content.edit.title" /></span>
				<html:text name="newsForm" property="newsTitle"
					value="${newsForm.news.newsTitle}" />
			</div>
			<div id="input_wrap_date">
				<span><bean:message key="content.edit.date" /></span>
				<html:text name="newsForm" property="date" value="${newsForm.date}" />
			</div>
			<div id="input_wrap_brief">
				<span><bean:message key="content.edit.brief" /></span>
				<html:textarea name="newsForm" property="brief"
					value="${newsForm.news.brief}" />
			</div>
			<div id="input_wrap_content">
				<span><bean:message key="content.edit.content" /></span>
				<html:textarea name="newsForm" property="content"
					value="${newsForm.news.content}" />
			</div>
			<div id="button_wrap">
				<html:submit>
					<bean:message key="content.edit.button.save" />
				</html:submit>
				<html:link action="/news?method=listNews">
					<button type="button">
						<bean:message key="content.edit.button.cancel" />
					</button>
				</html:link>
			</div>
		</html:form>
	</div>
</div>