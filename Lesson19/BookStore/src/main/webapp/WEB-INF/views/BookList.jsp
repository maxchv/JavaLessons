<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Book List</title>
<link rel="stylesheet" type="text/css"
	href='<spring:url value="/resources/css/main.css"></spring:url>' />
</head>
<body>
	<h1>Book List</h1>
	<c:url var="addBook" value="/input-book" />
	<a href="${addBook}">Add Book</a>
	<table>
		<thead>
			<tr>
				<th>Id</th>
				<th>Category</th>
				<th>Title</th>
				<th>ISBN</th>
				<th>Author</th>
				<th>Price</th>
				<th>&nbsp;</th>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${books}" var="book"> 
			<tr>
				<td>${book.id}</td>
				<td>${book.category.name}</td>
				<td>${book.title}</td>
				<td>${book.isbn}</td>
				<td>${book.author}</td>
				<td>${book.price}</td>
				<td><a href="edit-book/${book.id}">Edit</a></td>
			</tr>
		</tbody>
		</c:forEach>
	</table>
</body>
</html>