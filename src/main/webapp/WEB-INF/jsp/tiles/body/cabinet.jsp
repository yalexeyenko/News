<%@ page contentType="text/html;charset=UTF-8" language="java"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>

<div class="cabinet">
	<form:form action="deleteNewsList" modelAttribute="listOfCheckboxes">
		<c:forEach items="${page.content}" var="newsItem">
			<div class="news_item_block">
				<div class="title_date">
					<div class="title">
						<span>${newsItem.title}</span>
						<label>(<spring:message code="${newsItem.status}"  />)</label>						
					</div>
					<div class="date">
						<span>${newsItem.date}</span>
					</div>
				</div>
				<div class="news_brief">
					<span>${newsItem.brief}</span>
				</div>
				<div class="view_n_edit_ref">
					<a href="showViewNews?id=${newsItem.id}"> <spring:message
							code="content.list.item.ref.view" />
					</a>
					<c:if test="${newsItem.status ne 'approved'}">
						|<a href="showEditNews?id=${newsItem.id}"> <spring:message
								code="content.list.item.ref.edit" />
						</a>
					</c:if>					
					<form:checkbox path="idList" value="${newsItem.id}" />
				</div>
			</div>
		</c:forEach>
		<div class="multi_delete">
			<form:button>
				<spring:message code="content.list.button.delete" />
			</form:button>
		</div>
	</form:form>
	<div id="paging">
		<c:if test="${page.number > 0}">
			<a href="cabinet?pageNumber=${page.number - 1}">&lt;</a>
		</c:if>
		<label>${page.number + 1}</label>
		<c:if test="${page.totalPages > page.number + 1}">
			<a href="cabinet?pageNumber=${page.number + 1}">&gt;</a>
		</c:if>		
	</div>
</div>