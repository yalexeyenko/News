<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib uri="http://struts.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean"%>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic"%>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html"%>

<div class="listNewsContent">
<logic:iterate name="newsForm" property="newsList" id="news">
		<div class="">
			<div class="news_title"><span><bean:write name="news" property="newsTitle" /></span></div>
			<div class="date_brief"><span><bean:write name="news" property="date" /></span></div>
			<div class="news_brief"><span><bean:write name="news" property="content" /></span></div>
			<div class="view_n_edit_ref">
				<html:link action="/news?method=viewNews" >view</html:link>
				<html:link action="/news?method=editNews" >edit</html:link>
			</div>
		</div>	
	</logic:iterate>
</div>