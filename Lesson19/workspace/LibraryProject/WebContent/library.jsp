﻿<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%-- if(request.getAttribute("name") == null)	{
		response.sendRedirect("index.jsp");
	 } --%>
<%
	String name = null;
	Cookie[] cookies = request.getCookies();
	for(Cookie cookie: cookies) {
		//out.println(cookies.length);
		if(cookie.getName().equals("name")) {
			name = cookie.getValue();
		} 
	}
	if(name == null) {
		response.sendRedirect("index.jsp");
	}
	request.setAttribute("name", name);
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>Библиотека</title>
</head>
<body>
	
	
	<h1>Добро пожаловать <%= request.getAttribute("name") %> в библиотеку</h1>
	<form action="Logout">
		<button type="submit">Выйти</button>
	</form>
</body>
</html>