<%@page import="org.apache.naming.java.javaURLContextFactory"%>
<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Insert title here</title>
</head>
<body>
	<%-- Комментарий исходного кода JSP --%>
	<!-- Комментарий HTML разметки  -->
	<jsp:include page="head.html"></jsp:include>
	<%! 
		int count = 0;
	%> 
	<%= new java.util.Date() %>
	<p>
		Количество обращений к странице: <%= ++count %>
	</p>
	
	<p>Информация о клиенте:</p>
	<ul>
	<li>
		<%= request.getHeaderNames() %>
	</li>
	</ul>
	<p>
		Имя вашего хоста <%= request.getRemoteHost() %>
	</p>
	<pre>
	<%
		out.println("Protocol: "+request.getProtocol());
	%>
	</pre>
</body>
</html>