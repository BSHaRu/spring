<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style>

	table {
		width:100%;
	}
	
	.fileDrop{
		width:100%;
		height:150px;
		border:1px solid gray;
		background-color:lightslategray;
		margin:auto;
	}
	
	.uploadList{
		width:100%;
	}
	
	.uploadList li{
		float:left;
		padding:20px;
	}
	
	ul li{
		list-style:none;
	}
	
	.clear{
		clear:both;
	}
	
	li a{
		font-size:12px;
		text-decoration:none;
		color:gray;
	}
	
</style>
</head>
<body>
	<script src="http://code.jquery.com/jquery-latest.min.js"></script>
	<script>var contextPath = '${pageContext.request.contextPath}';</script>
	<script src="${pageContext.request.contextPath}/resources/js/upload.js"></script>
	<br/>
	<c:choose>
		<c:when test="${!empty userInfo}">
			<span>${userInfo.uname}</span>
			<a href="user/signOut">로그아웃</a>
		</c:when>
		<c:otherwise>
			<a href="user/signIn">로그인</a> 
			<a href="user/signUp">회원가입</a>
		</c:otherwise>
	</c:choose>
	<br/>
	<br/>
	<br/>
	<div>
		<label>FILE DROP HERE</label>
		<div class="fileDrop"></div>
	</div>
	<div>
		<ul class="uploadList">
			
		</ul>
	</div>
	
	<script>
	$(".fileDrop").on("dragenter dragover",function(event){
		event.preventDefault();
	});
	
	$(".fileDrop").on("drop",function(event){
		event.preventDefault();
		
		var files = event.originalEvent.dataTransfer.files;
		
		var maxSize = 10485760;
		
		var formData = new FormData();
		
		for(var i=0; i<files.length;i++){
			if(files[i].size > maxSize){
				alert("업로드 할 수 없는 파일이 포함되어 있습니다."+files[i].size);
				return;
			}
			console.log(files[i]);
			formData.append("file",files[i]);
		}
		
		$.ajax({
			type : "POST",
			data : formData,
			url : contextPath+"/uploadFile",
			dataType : "json",
			processData : false,
			contentType : false,
			success : function(data){
				console.log(data.length);
				for(var i =0; i< data.length; i++){
					console.log(data[i]);
					var fileInfo = getFileInfo(data[i]);
					
					var html = "<li>";
						html += "<img src='"+fileInfo.imgSrc+"' alt='attachment'/>";
						html += "<div>";
						html += "<a href='"+fileInfo.getLink+"' target='_blank'>";
						html += fileInfo.fileName;
						html += "</a>";
						html += "&nbsp;&nbsp;&nbsp;";
						html += "<a href='"+fileInfo.fullName+"' class='delBtn'>[삭제]</a>";
						html += "</div>";
						html += "</li>";
					$(".uploadList").append(html);
				}
			},
			error : function(res){
				alert(res.responseText);
			}
		});
	});	
	
	$(".uploadList").on("click",".delBtn",function(event){
		event.preventDefault();
		var target = $(this);
		
		$.ajax({
			type : "POST",
			url : contextPath+"/deleteFile",
			data : { 
				fileName : target.attr("href")
			},
			dataType : "text",
			success : function(data){
				target.closest("li").remove();
			}
		});
	});
	</script>
</body>
</html>