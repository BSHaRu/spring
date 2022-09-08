<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="f" %>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>listPage.jsp</title>
</head>
<body>
	<!-- model : list, pm -->
	<h3>Board List Page</h3>
	<button onclick="location.href='register';">New board</button>
	<h3>List</h3>
	<table border=1>
		<tr>
			<th>Bno</th>
			<th>Title</th>
			<th>Writer</th>
			<th>Regdate</th>
			<th>Viewcnt</th>
		</tr>
		<c:forEach var="board" items="${list}">
			<tr>
				<th>${board.bno }</th>
				<th>
					<%-- <a href="readPage?page=${pm.cri.page}&perPageNum=${pm.cri.perPageNum}&bno=${board.bno}"> --%>
					<a href="readPage${pm.mkQueryStr(pm.cri.page)}&bno=${board.bno}">
						${board.title }</a>
				</th>
				<th>${board.writer }</th>
				<th>
					<f:formatDate pattern="yyyy-MM-dd HH:mm" 
							value="${board.regdate }" />
				</th>
				<th>${board.viewcnt }</th>
			</tr>
		</c:forEach>
			<c:if test="${!empty pm and pm.maxPage > 1 }">
				<tr>
					<th colspan="5">
						<c:if test="${pm.first }">
							<a href="listPage?page=1">[&laquo;]</a>
						</c:if>
						<c:if test="${pm.prev }">
							<a href="listPage?page=${pm.startPage-1}">[&lt;]</a>
						</c:if>
						<c:forEach var="i" begin="${pm.startPage }" end="${pm.endPage }">
							<a href="listPage?page=${i }">[${i }]</a>
						</c:forEach>
						<c:if test="${pm.next }">
							<a href="listPage?page=${pm.endPage+1 }">[&gt;]</a>
						</c:if>
						<c:if test="${pm.last }">
							<a href="listPage?page=${pm.maxPage }">[&raquo;]</a>
						</c:if>
					</th>
				</tr>
			</c:if>
	</table>
</body>
</html>