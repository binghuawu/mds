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
			<li><c:out value="${a.name} - ${a.detail.addr}"></c:out>
				<p>Projects:</p>
				<ol>
					<c:forEach items="${a.projects}" var="b">
						<li><c:out value="${ b.prj.name}"></c:out></li>
					</c:forEach>
				</ol></li>
		</c:forEach>
	</ol>
</body>
</html>
