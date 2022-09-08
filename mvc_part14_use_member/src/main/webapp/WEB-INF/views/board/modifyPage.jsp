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
<title>board/modifyPage.jsp</title>
</head>
<style>
	.fileDrop{
		width:100%;
		height:150px;
		border:1px solid gray;
		background-color:lightgray;
	}

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
	<h2>
		<a href="${path}">HOME</a>
	</h2>
	<h3>MODIFY PAGE</h3>
	<form id="modifyForm" action="modifyPage" method="POST">
		<input type="hidden" name="uno" value="${userInfo.uno}"/>
		<input type="hidden" name="bno" value="${board.bno }" />
		<table>
			<tr>
				<td>제목</td>
				<td><input type="text" name="title" value="${board.title}" required/></td>
			</tr>
			<tr>
				<td>작성자</td>
				<td>
					${sessionScope.userInfo.uname}
				</td>
			</tr>
			<tr>
				<td>내용</td>
				<td>
					<textarea name="content" rows="30" cols="50">${board.content}</textarea>
				</td>
			</tr>
			<tr>
				<th colspan="2">
					<input type="button" id="saveBtn" value="등록"/>
				</th>
			</tr>
		</table>
		<div>
			<label>업로드 파일</label>
			<div class="fileDrop">
				
			</div>
		</div>
		<div>
			<ul class="uploadList">
				<c:if test="${!empty board.files}">
					<c:forEach var="file" items="${board.files}">
						<li data-src='${file}'>
							<c:choose>
								<c:when test="${fn:contains(file,'s_')}">
									<!-- 이미지 파일 -->
									<img src="${path}/displayFile?fileName=${file}"/>
									<div>
										<a href="${path}/displayFile?fileName=${fn:replace(file,'s_','')}">
											${fn:substringAfter(fn:substringAfter(file,"s_"),'_')}
										</a>
									</div>
								</c:when>
								<c:otherwise>
									<!-- 일반 파일 -->
									<img src="${path}/resources/img/file.png"/>
									<div>
										<a href="${path}/displayFile?fileName=${file}">
											${fn:substringAfter(file,'_')}
										</a>
									</div>
								</c:otherwise>
							</c:choose>
							&nbsp;&nbsp;
							<a href="${file}" class="delBtn">[삭제]</a>
						</li>
					</c:forEach>
				</c:if>
			</ul>
		</div>
	</form>
<script src="http://code.jquery.com/jquery-latest.min.js"></script>
<script> var contextPath = '${path}';</script>
<script src="${path}/resources/js/upload.js"></script>
<script>
	$(".fileDrop").on("dragenter dragover",function(e){
		e.preventDefault();
	});
	
	$(".fileDrop").on("drop",function(e){
		e.preventDefault();
		var files = e.originalEvent.dataTransfer.files;
		var formData = new FormData();
		for(var i = 0; i < files.length; i++){
			formData.append("file",files[i]);
		}
		
		$.ajax({
			type : "POST",
			url : "${path}/uploadfile",
			data : formData,
			processData : false,
			contentType : false,
			dataTyep : "json",
			success : function(data){
				$(data).each(function(){
					console.log(this);
					var fileInfo = getFileInfo(this);
					var html = "<li>";
					html += "<img src='"+fileInfo.imgSrc+"' alt='upload image' >";
					html += "<div>";
					html += "<a href='"+fileInfo.getLink+"'>";
					html += fileInfo.fileName;
					html += "</a>";
					html += "<a href='"+this+"' class='delBtn'>[삭제]</a>";
					html += "</div>";
					html += "</li>";
					$(".uploadList").append(html);
				});
			}
		});
	});
	
	var arr = [];
	$(".uploadList").on("click",".delBtn",function(e){
		e.preventDefault();
		var fileLink = $(this).attr("href");
		arr.push(fileLink);
		$(this).closest("li").remove();
		console.log(arr);
	});
	
	$("#saveBtn").click(function(){
		var str = "";
		$(".uploadList .delBtn").each(function(index){
			var fullName = $(this).attr("href");
			str += "<input type='hidden' name='files["+index+"]' value='"+fullName+"' />";
		});
		$("#modifyForm").append(str);
		
		$.post(
			contextPath+"/deleteAllFiles", 
			{files : arr},
			function(result){
				alert(result);
		});
		
		$("#modifyForm").submit();
	});
</script>
</body>
</html>