<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib uri="http://struts.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean"%>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic"%>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<div class="addContent">
	<bean:define id="newsItem" name="newsForm" property="news"></bean:define>
	<div class="add_news_form">
		<html:form action="/news?method=addNews">
			<div class="input_wrap">
				<span><bean:message key="content.add.title" /></span>
				<html:text name="newsItem" property="newsTitle" value="" />
			</div>
			<div class="input_wrap">
				<span><bean:message key="content.add.date" /></span>
				
<%-- 				<c:set value="<bean:xxxxxx>" var="varialble"/> --%>
<%-- 				<fmt:formatDate value="${newsItem.date}" pattern="${varialble}"/> --%>
				
				
				<html:text name="newsItem" property="date" value="${newsForm.news.date}" />
				
				
				
				
				
				
			</div>
			<div class="input_wrap">
				<span><bean:message key="content.add.brief" /></span>
				<html:textarea name="newsItem" property="brief" value="" />
			</div>
			<div class="input_wrap">
				<span><bean:message key="content.add.content" /></span>
				<html:textarea name="newsItem" property="content" value="" />
			</div>
			<div class="button_wrap">
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