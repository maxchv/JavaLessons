<%@page import="org.apache.tomcat.jni.Directory"%>
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
	<%@page import="java.io.File" %>
	<% 
		File upload = new File("C:\\Users\\shaptala\\Documents\\GitHub\\JavaLessons\\Lesson19\\workspace\\Gallery\\WebContent\\uploads");
		if(upload.exists()) {
			for(File f: upload.listFiles()) {
				//out.append(""+f.canRead());
				out.append("<p style='float: left; margin: 10px'><a href='uploads/"+f.getName()+"'>"+
						"<img src='http://localhost:8080/Gallery/uploads/"+f.getName()+"' width='100'></a></p>");
			}
		}		
	%>
</body>
</html>