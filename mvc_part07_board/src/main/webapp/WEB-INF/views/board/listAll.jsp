<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>listAll.jsp</title>
</head>
<body>
	<h3>Board List All</h3>
	<a href="register">New Board</a>
	<h3>List - ${result }</h3>
	<table border=1>
		<tr>
			<th>Bno</th>
			<th>Title</th>
			<th>Writer</th>
			<th>Regdate</th>
			<th>Viewcnt</th>
		</tr>
		<c:choose>
			<c:when test="${!empty list }">
				<c:forEach var="board" items="${list}">
					<tr>
						<td>${board.bno }</td>
						<td>
							<a href="read?bno=${board.bno}">${board.title}</a>
						</td>
						<td>${board.writer }</td>
						<td>${board.regdate }</td>
						<td>${board.viewcnt }</td>
					</tr>
				</c:forEach>
			</c:when>
			<c:otherwise>
				<tr>
					<th colspan=5>
						등록된 게시물이 없습니다.
					</th>
				</tr>
			</c:otherwise>
		</c:choose>
	</table>
	
	<script>
		/* '' 안붙여도 되는데 ''안붙였을때 해당값이 없으면 문법적 오류이다
		-> var result = ; | var result = '';
		=> ''을 붙이면 해당값이 존재 하지 않아도 빈문자열이 들어가기때문에 오류가 안나서 코드가 더 안정적임 */
		var result = '${result }';
		if(result != null && result != ''){
			alert(result);
		}
	</script>
	
</body>
</html>