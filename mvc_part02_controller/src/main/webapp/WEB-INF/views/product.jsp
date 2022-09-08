<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>product.jsp</title>
</head>
<body>
	<h2>${requestScope.product.name }</h2>
	<h2>${requestScope.product.price }</h2>
	<hr/>
	<!-- ProductVO == productVO(첫글자만 소문자로 변경해서 지정) -->
	<h2>${productVO.name }</h2>
	<h2>${productVO.price }</h2>
</body>
</html>