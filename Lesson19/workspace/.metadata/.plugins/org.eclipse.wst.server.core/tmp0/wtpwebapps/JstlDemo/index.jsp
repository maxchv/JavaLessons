<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
    <%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
    <%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
    <%@taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql" %>
    <%@taglib uri="http://java.sun.com/jsp/jstl/xml" prefix="x" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>Insert title here</title>
</head>
<body>
	<%-- request.setAttribute("msg", "Welcome!"); --%>
	<c:set var="msg" value="Welcome!"/> 
	<c:out value='${msg}' />
	<c:remove var="${msg}"/>
	
	<c:catch var="catchException">
		<% int a = 10/0; %>
	</c:catch>
	
	
	<c:if test="${catchException != null}">
		<p>The exception is : ${catchException} <br />
   		There is an exception: ${catchException.message}</p>
	</c:if>
	<c:set var="data" value="10" scope="page"/>
	<c:choose>
		<c:when test="${data > 10}">
			<p>Data &gt; 10</p>
		</c:when>
		<c:when test="${data < 10}">
			<p>Data &lt; 10</p>
		</c:when>
		<c:otherwise>
			<p>Data = 10</p>
		</c:otherwise>
	</c:choose>

</body>
</html>