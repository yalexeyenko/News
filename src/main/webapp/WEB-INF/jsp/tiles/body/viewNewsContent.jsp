<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib uri="http://struts.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean"%>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic"%>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html"%>

<div class="viewNewsContent">
	<div class="view_news">
		<div class="view_wrap">
			<span>News Title</span>
			<p>${newsForm.news.newsTitle}</p>
		</div>
		<div class="view_wrap">
			<span>News Date</span>
			<p>${newsForm.news.date}</p>
		</div>
		<div class="view_wrap">
			<span>Brief</span>
			<p>${newsForm.news.brief}</p>
		</div>
		<div class="view_wrap">
			<span>Content</span>
			<p>${newsForm.news.content}</p>
		</div>
		<div class="edit_n_delete_ref">
				<html:link action="/news?method=showEditNews" >EDIT
					<html:param name="id" value="${newsForm.news.id}"></html:param>
				</html:link>
				<html:link action="/news?method=deleteNews" >DELETE
					<html:param name="id" value="${newsForm.news.id}"></html:param>
				</html:link>
			</div>
	</div>
</div>