<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<html>
<head>
	<title>Home</title>
</head>
<body>
<h1>
	Hello Admin! 
</h1>

<c:url var="url" value="/admin/save" />
<form:form method="post" action="${url}" commandName="post" >
	<form:label path="title">Title</form:label>
	<br />
	<form:input id="title" path="title"/>
	<br />
	<form:label path="text">Text</form:label>
	<br />
	<form:textarea id="text" path="text" cols="22" rows="5"/>
	<br />
	<form:button>Save</form:button>
</form:form> 

<c:url var="home" value="/" />
<a href="${home}">Go home</a>

</body>
</html>
