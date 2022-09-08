<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>register.jsp</title>
</head>
<body>
	<h1>Register Board</h1>
	<!-- action이 없으면 현재 페이지 그대로 요청을 받음 -->
	<form method="post">
		<div>
			<label>Title</label>
			<input type="text" name="title" />
		</div>
		<div>
			<label>Content</label>
			<textarea name="content" rows=3></textarea>
		</div>
		<div>
			<label>Writer</label>
			<input type="text" name="writer" />
		</div>
		<div>
			<input type="submit" value="글작성 완료" />
		</div>
	</form>
</body>
</html>