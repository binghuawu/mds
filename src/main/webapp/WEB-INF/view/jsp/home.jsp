<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page session="true" pageEncoding="utf-8"%>
<html>
<head>
<title>Home</title>
</head>
<body>
	<h1>Hello world!</h1>

	<P>The time on the server is ${serverTime}.</P>

	<h2>
		Counter =
		<%=request.getSession().getAttribute("counter")%>
	</h2>
	<c:forEach items="${list}" var="a">
		<c:out value="${a}"></c:out>
	</c:forEach>
</body>
</html>
