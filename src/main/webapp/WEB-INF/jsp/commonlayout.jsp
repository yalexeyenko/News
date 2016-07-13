<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>

<link rel="stylesheet" href="resources/css/style.css" type="text/css">

<html>
<head>
<title><spring:message code="<tiles:getAsString name='title'/>"/></title>
</head>
<body>
	<div class="wrapper">
		<tiles:insertAttribute name="header" />
		<div class="content">
			<tiles:insertAttribute name="leftMenu" />
			<tiles:insertAttribute name="content" />
		</div>
	</div>
	<tiles:insertAttribute name="footer" />
</body>
</html>