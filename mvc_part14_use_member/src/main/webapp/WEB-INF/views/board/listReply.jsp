<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="f" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>    
<c:set var="path" value="${pageContext.request.contextPath }" />   
 
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>board/listReply.jsp</title>
</head>
<body>
	<h1><a href="${path }">Home</a></h1>
	<div>
		<select>
			<option value="n">------------------------------</option>
			<option value="t">제목</option>
			<option value="c">내용</option>
			<option value="w">작성자</option>
			<option value="tc">제목 &amp; 내용</option>
			<option value="tw">제목 &amp; 작성자</option>
			<option value="tcw">제목 &amp; 내용 &amp; 작성자</option>
		</select>
		<input type="text" id="keyword" />
		<input type="button" value="검색" id="searchBtn" />
		<c:if test="${!empty sessionScope.userInfo }">
			<input type="button" value="글작성" id="newBtn" />
		</c:if>
	</div>
	<br/>
	<!-- 게시글 목록 -->
	<table border="1">
		<tr>
			<th>번호</th>
			<th>제목</th>
			<th>작성자</th>
			<th>작성일</th>
			<th>조회수</th>
		</tr>
		<!-- 게시글 리스트 목록 출력  name : list -->
		<c:choose>
			<c:when test="${!empty list }">
				<c:forEach var="board" items="${list }">
					<c:choose>
						<c:when test="${board.showboard eq 'y'}">
							<tr>
								<td>${board.bno }</td>
								<td>
									<a href="readPage?bno=${board.bno }">
									<c:forEach var="i" begin="1" end="${board.depth }">
										&nbsp;&nbsp;
									</c:forEach>
									<c:if test="${board.depth !=0}">
										↳
									</c:if>
									${board.title }</a>
								</td>
								<td>${board.writer }</td>
								<td>
									<f:formatDate value="${board.regdate }" 
									pattern="yyyy-MM-dd (E) hh:mm" />
								</td>
								<td>${board.viewcnt }</td>
							</tr>
						</c:when>
						<c:otherwise>
							<tr>
								<td></td>
								<td>삭제된 게시물 입니다.</td>
								<td></td>
								<td></td>
								<td></td>
							</tr>
						</c:otherwise>
					</c:choose>
				</c:forEach>
			</c:when>
			<c:otherwise>
				<tr>
					<td></td>
					<td>등록된 게시물이 없습니다.</td>
				</tr>
			</c:otherwise>
		</c:choose>	
		<!-- 페이징 블럭 출력 name : pm-->
		<tr>
			<th colspan="5">
				<c:forEach var="i" begin="${pm.startPage }" end="${pm.endPage }">
					<a href="listReply?page=${i }">[${i }]</a>
				</c:forEach>
			</th>
		</tr>
	</table>
<script src="http://code.jquery.com/jquery-latest.min.js"></script>
<script>
	// 새글 작성 버튼 클릭
	$("#newBtn").click(function(){
		location.href='${path}/board/register';
	});
	
	// 검색
	$("#searchBtn").click(function(){
		var searchType = $("select option:selected").val();
		var keyword = $("#keyword").val();
		console.log("searchType : " + searchType);
		console.log("keyword : " + keyword);
		location.href="listReply?searchType="+searchType+"&keyword="+keyword;
	});
</script>
</body>
</html>