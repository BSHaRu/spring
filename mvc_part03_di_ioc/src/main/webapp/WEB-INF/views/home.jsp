<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>home.jsp</title>
</head>
<body>
	<img src="imgs/camera.png" />
	<!-- DispatcherServlet(servlet-context.xml)를 따로 설정 안해주면 
		해당 경로(/imgs/camera.png)로 가려고 하기때문에
		해당 파일을 처리하려면 설정을 바꾸던지 resources폴더에 넣어서 처리해야됨
	 -->
	<br/>
	<hr/>
	<img src="resources/file.png" />
	<br/>
	<hr/>
	<img src="resources/camera.png" />
	<br/>
	<hr/>
	<h3><a href="second/main">second-main</a></h3>
</body>
</html>