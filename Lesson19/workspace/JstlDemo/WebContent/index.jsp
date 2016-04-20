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
	<c:remove var="msg"/>
	
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

	<sql:setDataSource var="studentsDb" url="jdbc:mysql://localhost/students" user="root" password="" driver="com.mysql.jdbc.Driver"/>
	<sql:query dataSource="${studentsDb}" var="result">
		select Id, UserName, FirstName, LastName from students;
	</sql:query> 
	<table border="1" width="100%">
		<thead>
			<tr>
				<th>Id</th>				
				<th>UserName</th>				
				<th>FirstName</th>
				<th>LastName</th>				
			</tr>
		</thead>
		<tbody>
			<c:forEach var="row" items="${result.rows}">
				<tr>
					<td><c:out value="${row.Id}"></c:out></td>
					<td><c:out value="${row.UserName}"></c:out></td>
					<td><c:out value="${row.FirstName}"></c:out></td>
					<td><c:out value="${row.LastName}"></c:out></td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
	
	
</body>
</html>