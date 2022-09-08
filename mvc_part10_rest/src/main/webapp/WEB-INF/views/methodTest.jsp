<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>methodTest.jsp</title>
</head>
<body>
	<h1> POST </h1>
	<form action="methodTest" method="POST">
		<input type="text" name="name" /> <br/>
		<input type="number" name="age" /> <br/>
		<input type="submit" value="POST" /> 
	</form>
	<hr/>
	
	<h1> PUT </h1>
	<form action="methodTest" method="POST">
		<!-- form태그에 method는 get과 post밖에 없는데 
		조금 설정 추가 해주면 hidden으로 put | delete | patch로 전달 가능하다  -->
		<input type="hidden" name="_method" value="PUT" />
		<input type="text" name="name" /> <br/>
		<input type="number" name="age" /> <br/>
		<input type="submit" value="PUT" /> 
	</form>
	<hr/>
	
</body>
</html>