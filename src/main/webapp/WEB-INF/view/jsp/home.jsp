<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page session="true" pageEncoding="utf-8"%>
<html>
<head>
<title>Home</title>
</head>
<body>
	<h1>Hello world!</h1>

	<P>The time on the server is ${serverTime}.</P>

	<ol>
		<c:forEach items="${users}" var="a">
			<li><c:out value="${a.name} - ${a.detail.addr} }"></c:out></li>
		</c:forEach>
	</ol>
</body>
</html>
