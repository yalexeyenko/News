<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib uri="http://struts.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean"%>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic"%>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html"%>

<div class="addContent">
	<div class="add_news_form">
		<html:form action="/news?method=addNews">
			<div class="input_wrap">
				<span>News Title</span>
				<html:text name="newsForm" property="newsTitle" value="" />
			</div>
			<div class="input_wrap">
				<span>News Date</span>
				<html:text name="newsForm" property="date" value="${newsForm.date}" />
			</div>
			<div class="input_wrap">
				<span>Brief</span>
				<html:textarea name="newsForm" property="brief" value="" />
			</div>
			<div class="input_wrap">
				<span>Content</span>
				<html:textarea name="newsForm" property="content" value="" />
			</div>
			<div class="button_wrap">
				<html:submit>SAVE</html:submit>
				<html:reset>CANCEL</html:reset>
			</div>
		</html:form>
	</div>
</div>