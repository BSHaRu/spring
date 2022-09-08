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
<title>board/readPage.jsp</title>
<style>
	.uploadList{
		width:100%;
	}
	
	.uploadList li{
		text-align:center;
		display:inline-block;
		padding:20px;
		list-style:none;
	}
</style>
</head>
<body>
	<h2><a href="${path }/board/listReply">리스트 목록</a></h2>
	<h3>Read Page</h3>
	<!-- 게시글 정보 name : board -->
	<table>
		<tr>
			<td>제목</td>
			<td>${board.title }</td>
		</tr>
		<tr>
			<td>작성자</td>
			<td>${board.writer }</td>
		</tr>
		<tr>
			<td>내용</td>
			<td>${board.content }</td>
			<%-- 
			<td>
				<textarea rows="30" cols="50" readonly>${board.content }</textarea>
			</td> 
			--%>
		</tr>
	</table>
	<hr/>
	
	<!-- 첨부파일 있으면 그거 보여줌 -->
	<div>
		<ul class="uploadList">
			<c:if test="${!empty board.files}">
				<c:forEach var="file" items="${board.files}">
					<li data-src='${file }'>
						<c:choose>
							<%-- 파일 이름에 s_가 있는지 확인을 통해서 이미지 파일인지 일반파일인지 구분 --%>
							<c:when test="${fn:contains(file,'s_') }"> 
								<!-- 이미지 파일 -->
								<img src="${path}/displayFile?fileName=${file}" />
								<div>
									<a href="${path}/displayFile?fileName=${fn:replace(file,'s_','')}">
										${fn:substringAfter(fn:substringAfter(file,"s_"),'_')}
									</a>
								</div>
							</c:when>
							<c:otherwise>
								<!-- 일반 파일 -->
								<img src="${path }/resources/img/file.png" />
								<div>
									<a href="${path }/displayFile?fileName=${file}" >
										${fn:substringAfter(file,'_') }
									</a>
								</div>
							</c:otherwise>
						</c:choose>
					</li>
				</c:forEach>
			</c:if>
		</ul>		
	</div>
	
	<!-- 수정 삭제 답글 버튼 -->
	<div>
		<c:if test="${!empty userInfo }">
			<c:if test="${userInfo.uno eq board.uno}">
			 	<input type="button" id="modifyBtn" value="수정" />
			 	<input type="button" id="removeBtn" value="삭제" />
			</c:if>
			<input type="button" id="replyBtn" value="답글" />
		</c:if>
	</div>
	<form id="readForm">
		<input type="hidden" name="csrf_token" value="${csrf_token }" />
		<input type="hidden" name="bno" value="${board.bno }" />
	</form>
<script src="http://code.jquery.com/jquery-latest.min.js"></script>
<script>
	var formObj = $("#readForm");
	$("#replyBtn").click(function(){
		formObj.attr("action", "replyRegister").submit();
	});
	
	$("#removeBtn").click(function(){
		var isDelete = confirm("게시글을 삭제 할꺼임?");
		if(isDelete){
			var arr = [];
			$(".uploadList li").each(function(){
				var fileName = $(this).attr("data-src"); // this == li요소
				arr.push(fileName);
			});
			console.log(arr);
			if(arr.length > 0){
				// $.post(url, parameters, callback함수) 
				$.post(
					"${path}/deleteAllFiles",
					{files : arr},
					function(result){
						alert(result);
				});
			}
			formObj.attr("action", "remove");
			formObj.attr("method", "POST");
			formObj.submit();
		}else{
			alert('삭제 취소 됨');
		}
	});
	
	// 수정 페이지 이동
	$("#modifyBtn").click(function(){
		formObj.attr("action", "modifyPage").submit();
	});
	
</script>
</body>
</html>