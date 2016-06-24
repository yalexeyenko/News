<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib uri="http://struts.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean"%>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic"%>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html"%>

<div class="viewNewsContent">
	<bean:define id="newsItem" name="newsForm" property="news"/>
	<div class="view_news">
		<div class="view_wrap">
			<span><bean:message key="content.view.title" /></span>
			<span><bean:write name="newsItem" property="newsTitle" /></span>
		</div>
		<div class="view_wrap">
			<span><bean:message key="content.view.date" /></span>
			<span><bean:write name="newsItem" property="date"
								formatKey="format.date" /></span>
		</div>
		<div class="view_wrap">
			<span><bean:message key="content.view.brief" /></span>
			<span><bean:write name="newsItem" property="brief" /></span>
		</div>
		<div class="view_wrap">
			<span><bean:message key="content.view.content" /></span>
			<span><bean:write name="newsItem" property="content" /></span>
		</div>
		<div class="edit_n_delete_ref">
				<html:link action="/news?method=showEditNews" ><bean:message key="content.view.button.edit" />
					<html:param name="id" value="${newsForm.news.id}"></html:param>
				</html:link>
				<html:link action="/news?method=deleteNews" ><bean:message key="content.view.button.delete" />
					<html:param name="id" value="${newsForm.news.id}"></html:param>
				</html:link>
			</div>
	</div>
</div>