<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1>${ title }</h1>
	<c:url var="save" value="/products/save" />
	<form:form commandName="product" action="${save}" method="post">
		<form:hidden path="id"/>
		<label>
			title
			<form:input id="title" path="title" />
		</label>
		<br />
		<label>
			description
			<form:textarea id="description" path="description" />
		</label>
		<br />
		<label>
			price
			<form:input type="number" step="0.1"  id="price" path="price" />
		</label>
		<br />
		<button type="submit">Create</button>
	</form:form>
</body>
</html>