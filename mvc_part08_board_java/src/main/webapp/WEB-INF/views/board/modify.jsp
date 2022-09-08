<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>modify.jsp</title>
</head>
<body>
	<h3>Modify Board</h3>
	<!-- model boardVO -->
	<!-- board/modify post -->
	<form method="post">
		<input type="hidden" name="bno" value="${boardVO.bno }" />
		<div>
			<label>Title</label>
			<input type="text" name="title" value="${boardVO.title }" />
		</div>
		<div>
			<label>Content</label>
			<textarea name="content" rows=3>${boardVO.content }</textarea>
		</div>
		<div>
			<label>Writer</label>
			<input type="text" name="writer" value="${boardVO.writer }" />
		</div>
		<div>
			<input type="submit" value="수정 완료" />
			<a href="listAll">List</a>
		</div>
	</form>
</body>
</html>