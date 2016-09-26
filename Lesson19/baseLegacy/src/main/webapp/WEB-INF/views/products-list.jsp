<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link href='<s:url value="/resources/css/style.css"/>' rel="stylesheet" />
</head>
<body>
	<h1>${ title }</h1>
	<table>
	<thead>
		<tr>
			<th>id</th>
			<th>title</th>
			<th>description</th>
			<th>price</th>
		</tr>
	</thead>
	<tbody>
		<c:forEach var="product" items="${products}">
			<tr>
				<td>${product.id}</td>
				<td>${product.title}</td>
				<td>${product.description}</td>
				<td>${product.price}</td>
			</tr>
		</c:forEach>
	</tbody>
	</table>
	<c:url var="create" value="/products/create"></c:url>
	<p><a href="${create}">Create product</a></p>
</body>
</html>