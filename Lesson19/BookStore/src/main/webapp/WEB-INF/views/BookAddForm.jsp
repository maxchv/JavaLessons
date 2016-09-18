<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Add Book Form</title>
<link rel="stylesheet" type="text/css" href='<spring:url value="/resources/css/main.css"></spring:url>' />
</head>
<body>
<div id="global">
	<form:form commandName="book" action="save-book" method="post">
		<fieldset>
			<legend>Add a book</legend>
			<p>
				<label for="category">Category: </label>				
				<form:select id="category" 
				             path="category.id" 
				             items="${categories}"
				             itemLabel="name"
				             itemValue="id" />
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
				<input id="reset" type="reset"/>
				<input id="submit" type="submit" value="Add Book" />
			</p>
		</fieldset>
	</form:form>
</div>
</body>
</html>