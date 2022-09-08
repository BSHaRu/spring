<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>board/replyRegister.jsp</title>
</head>
<body>
	<h1><a href="${pageContext.request.contextPath }">Home</a></h1>
	<h3>Reply Register Board</h3>
	<form action="replyRegister" method="post">
		<input type="hidden" name="uno" value="${userInfo.uno }" />
		<input type="hidden" name="origin" value="${original.origin }" />
		<input type="hidden" name="depth" value="${original.depth }" />
		<input type="hidden" name="seq" value="${original.seq }" />
		<table>
			<tr>
				<td>제목</td>
				<td>
					<input type="text" name="title" required/>
				</td>
			</tr>
			<tr>
				<td>작성자</td>
				<td>
					${sessionScope.userInfo.uname }
				</td>
			</tr>
			<tr>
				<td>내용</td>
				<td>
					<textarea name="content" rows="30" cols="50"></textarea>
				</td>
			</tr>
			<tr>
				<th colspan="2">
					<input type="submit" value="등록" />
				</th>
			</tr>
		</table>
	</form>
</body>
</html>