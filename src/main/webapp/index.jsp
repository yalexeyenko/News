<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html"%>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean"%>

<html>
<head>
<title>News block</title>
</head>
<body>
	<h3>Dispatch Action Example</h3>

	<p>
		<html:link page="/user.do?parameter=add">Call Add Section</html:link>
	</p>
	<p>
		<html:link page="/user.do?parameter=edit">Call Edit Section</html:link>
	</p>
	<p>
		<html:link page="/user.do?parameter=search">Call Search Section</html:link>
	</p>
	<p>
		<html:link page="/user.do?parameter=save">Call Save Section</html:link>
	</p>

</body>
</html>