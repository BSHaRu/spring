<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>read.jsp</title>
</head>
<body>
	<h3>Read Board</h3>
	<!-- model boardVO -->
	<div>
		<label>Title</label>
		<input type="text" name="title" value="${boardVO.title }" readonly />
	</div>
	<div>
		<label>Content</label>
		<textarea name="content" rows=3>${boardVO.content }</textarea>
	</div>
	<div>
		<label>Writer</label>
		<input type="text" name="writer" value="${boardVO.writer }" readonly />
	</div>
	<div>
		<a href="modify?bno=${boardVO.bno }">Modify</a>
		<a href="remove?bno=${boardVO.bno }">Delete</a>
		<a href="listAll">List</a>
	</div>
</body>
</html>