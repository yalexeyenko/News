<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib uri="http://struts.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean"%>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic"%>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html"%>

<div class="listNewsContent">
	<html:form action="/news?method=deleteNews">
		<logic:iterate name="newsForm" property="newsList" id="news">
				<div class="">
					<div class="news_title"><span><bean:write name="news" property="newsTitle" /></span></div>
					<div class="date_brief"><span><bean:write name="news" property="date" /></span></div>
					<div class="news_brief"><span><bean:write name="news" property="brief" /></span></div>
					<div class="view_n_edit_ref">
						<html:link action="/news?method=showViewNews" ><bean:message key="content.list.item.ref.view" /> 
							<html:param name="id" value="${news.id}"></html:param>
						</html:link>
						<html:link action="/news?method=showEditNews" ><bean:message key="content.list.item.ref.edit" /> 
							<html:param name="id" value="${news.id}"></html:param>
						</html:link>
						<html:multibox property="itemsToDelete" value="${news.id}"/>
					</div>
				</div>	
		</logic:iterate>
		<html:submit><bean:message key="content.list.button.delete" /> </html:submit>
	</html:form>
</div>