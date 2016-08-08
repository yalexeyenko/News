<%@ page contentType="text/html;charset=UTF-8" language="java"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>

<div class="listNewsContent">
	<c:if test="${not empty page.content}">
		<c:forEach items="${page.content}" var="newsItem">
			<div class="news_item_block">
				<div class="title_date">
					<div class="title">
						<span>${newsItem.title}</span>
					</div>
					<div class="date">
						<span>${newsItem.date}</span>
					</div>
				</div>
				<div class="news_brief">
					<span>${newsItem.brief}</span>
				</div>
				<div class="view_n_edit_ref">
					<a href="showGuestViewNews?id=${newsItem.id}"> <spring:message
							code="content.list.item.ref.view" />
					</a>
				</div>
			</div>
		</c:forEach>
		<div id="paging">
			<c:if test="${page.number > 0}">
				<a href="home?pageNumber=${page.number - 1}">&lt;</a>
			</c:if>
			<label>${page.number + 1}</label>
			<c:if test="${page.totalPages > page.number + 1}">
				<a href="home?pageNumber=${page.number + 1}">&gt;</a>
			</c:if>
		</div>
	</c:if>
	<c:if test="${empty page.content}">
		<div id="no_news">
			<spring:message code="news.not.found" />
		</div>
	</c:if>
</div>