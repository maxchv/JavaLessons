<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Edit Book Form</title>
<link rel="stylesheet" type="text/css"
	href='<spring:url value="/resources/css/main.css"></spring:url>' />
</head>
<body>
	<c:url var="formAction" value="/update-book" />
	<form:form commandName="book" action="${formAction}" method="post">
		<fieldset>
			<legend>Edit a book</legend>
			<form:hidden path="id" />
			<p>
				<label for="category">Category: </label>
				<form:select id="category" items="${categories}" path="category.id"
					itemLabel="name" itemValue="id" />
			</p>
			<p>
				<label for="title">Title: </label>
				<form:input id="title" path="title" />
			</p>
			<p>
				<label for="author">Author: </label>
				<form:input id="author" path="author" />
			</p>
			<p>
				<label for="isbn">ISBN: </label>
				<form:input id="isbn" path="isbn" />
			</p>
			<p>
				<label for="price">Price: </label>
				<form:input id="price" path="price" />
			</p>
			<p id="buttons">
				<input id="reset" type="reset" value="Reset" />
				<input id="submit" type="submit" value="Update Book" />				
			</p>
		</fieldset>
	</form:form>
</body>
</html>