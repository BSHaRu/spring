<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="path" value="${pageContext.request.contextPath }" /> 

<!--  user/signUp.jsp    -->
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<div>
		<h2>회원 가입</h2>
		<div>
			<%-- <a href="${path }">Main</a> --%>
			<a href="<c:url value='/' />">Main</a>	<!-- 위에도 되고 이것도 된다고함 -->
		</div>
		<article>
			<form action="signUpPost" method="POST">
				<table>
					<tr>
						<td>아이디</td>
						<td>
							<input type="text" name="uid" alt="아이디" required />
						</td>
					</tr>
					<tr>
						<td>비밀번호</td>
						<td>
							<input type="password" name="upw" alt="비밀번호" required />
						</td>
					</tr>
					<tr>
						<td>비밀번호 확인</td>
						<td>
							<input type="password" name="repw" alt="비밀번호확인" required />
						</td>
					</tr>
					<tr>
						<td>사용자 이름</td>
						<td>
							<input type="text" name="uname" alt="사용자 이름" required />
						</td>
					</tr>
					<tr>
						<th colspan="2">
							<input type="submit" value="회원가입완료" />
							<input type="button" 
								onclick="location.href='singIn';"
								value="로그인" />
						</th>
					</tr>
				</table>
			</form>
		</article>
	</div>
	
	<script>
		var msg = '${message}';
		if(msg !=''){
			alert(msg);
		}
	</script>
	
</body>
</html>