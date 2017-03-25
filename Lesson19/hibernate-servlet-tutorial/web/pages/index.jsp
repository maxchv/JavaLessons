<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: User
  Date: 25.03.2017
  Time: 0:58
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>${title}</title>
</head>
<body>
<h1>${title}</h1>

<form method="post">
    <label>
        Title: <input name="title" id="title"/>
    </label>
    <br/>
    <label>
        Description: <textarea name="body" id="body" rows="5" cols="25"></textarea>
    </label>
    <br/>
    <label>
        Start: <input type="datetime-local" name="start" id="start"/>
    </label>
    <br/>
    <label>
        End: <input type="datetime-local" name="end" id="end"/>
    </label>
    <br/>
    <button type="submit">Save</button>
</form>

<table>
    <thead>
    <tr>
        <th>#</th>
        <th>Title</th>
        <th>Description</th>
        <th>Start</th>
        <th>End</th>
    </tr>
    </thead>

    <c:forEach var="item" items="${todos}" varStatus="status">
        <tr>
            <td>${item.id}/${status.count}</td>
            <td>${item.title}</td>
            <td>${item.body}</td>
            <td>${item.start}</td>
            <td>${item.end}</td>
        </tr>
    </c:forEach>
</table>

</body>
</html>
