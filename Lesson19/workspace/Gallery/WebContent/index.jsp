<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>Gallery</title>
</head>
<body>
	<form action="UploadImages" method="post" enctype="multipart/form-data">
		<input type="file" name="img"/>
		<input type="submit" value="Upload" />
	</form>
	<%
		Object msg = request.getAttribute("msg");
		if(msg != null) {
			out.append(msg.toString());
		}
	%>
</body>
</html>