<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<html>
<head>
	<title>Home</title>
	<meta charset="utf-8" />
	<spring:url var="style" value="/resources/css/style.css"></spring:url>
	<link rel="stylesheet" href="${style}" />
</head>
<body>
<h1>
	Hello world!  
</h1>
<table>
	<thead>
		<tr>
			<th>Title</th>
			<th>Text</th>
		</tr>
	</thead>
	<c:forEach items="${posts}" var="post">
		<tr>
			<td>${post.title}</td>
			<td>${post.text}</td>
		</tr>
	</c:forEach>
</table>
</body>
</html>
