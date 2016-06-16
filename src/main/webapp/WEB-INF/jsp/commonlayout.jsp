<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib uri="http://struts.apache.org/tags-tiles" prefix="tiles"%>
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/css/style.css" type="text/css">

<html>
<head>
<title><tiles:getAsString name="title" /></title>
</head>
<body>
	<div class="wrapper">
		<tiles:insert attribute="header" />
		<div class="content">
			<tiles:insert attribute="leftMenu" />
			<tiles:insert attribute="content" />
		</div>
	</div>
	<tiles:insert attribute="footer" />

</body>
</html>