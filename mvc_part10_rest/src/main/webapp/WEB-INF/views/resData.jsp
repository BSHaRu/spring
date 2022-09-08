<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>resData.jsp</title>
</head>
<body>
	<a href="testJSON">testJson</a>

	<h3>getSample</h3>		
	<!-- enctype : 인코딩 타입 | default : application/x-www-form-urlencoded  
	 - application/x-www-form-urlencoded : 모든 값을 서버로 전달하기 전에 인코딩 됨을 명시 
	 	-> 일반적인 form형태의 인코딩 방식임
	 - multipart/form-data : 인코딩을 전혀 하지 않고 있는 그대로 전송함
		->대용량의 데이터를 전송할 때 쓰는 방식
	 - text/plain : 공백 문자만 + 기호로  변환하고 나머지는 인코딩 하지 않음
	-->
	<form action="getSample" method="get" enctype="application/x-www-form-urlencoded">
		<input type="text" name = "name" /> <br/>
		<input type="number" name = "age" /> <br/>
		<input type="submit" value = "GET" />
	</form>
	<hr/>
	
	<a href="getSampleList">getSampleList</a> <br/>
</body>
</html>