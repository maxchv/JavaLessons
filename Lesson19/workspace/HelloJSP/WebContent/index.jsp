<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>Hello JSP</title>
</head>
<body>
	<!-- Привет, ты меня видишь? -->
	<%-- Комментарий JSP --%>
	<%@ page import="java.time.LocalTime" %>
	<%! LocalTime now = LocalTime.now(); %>
	<p>Сейчас: <%= LocalTime.now() %>, а это значит</p>
	<%
		response.setIntHeader("Refresh", 1);
	%>
</body>
</html>