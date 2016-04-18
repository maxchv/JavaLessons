<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
	Cookie[] cookies = request.getCookies();
	for (Cookie cookie : cookies) {
		if (cookie.getName().equals("name")) {
			response.sendRedirect("library.jsp");
		}
	}
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>Библиотека</title>
<link rel="stylesheet" href="css/style.css" />
</head>
<body>

	<form id="login" action="Login" method="post">
		<label> Login <input type="text" placeholder="login"
			name="login" />
		</label> <label> Password <input type="password"
			placeholder="password" name="password" />
		</label> <span id="error"> <%
 	Object error = request.getAttribute("error");
 	if (error != null) {
 		out.append(error.toString());
 	}
 %>
		</span>
		<button type="submit">Вход</button>
	</form>

</body>
</html>