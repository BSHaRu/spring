<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1>로그인 page</h1>
	<form method="POST" action="${pageContext.request.contextPath }/login">
		<input type="text" name="u_id" /> <br/>
		<input type="password" name="u_pw" />
		<div>
			<label>
				<input type="checkbox" name="rememberMe" />
				remember me
			</label>
		</div>
		<input type="submit" value="로그인" />
		<input type="hidden" name="${_csrf.parameterName }" value="${_csrf.token }" />
	</form>
</body>
</html>