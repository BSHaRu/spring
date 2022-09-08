<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="path" value="${pageContext.request.contextPath }" />
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>uploadAjax.jsp</title>
<style>
	.profile_img_wrap .img_cover > label{
		background-image:url('${path}/resources/img/camera.png');
		background-size:30px;
		background-repeat:no-repeat;
		width:30px;
		height:30px;
		display:inline-block;
		margin:0;
	}
	
	.profile_img_wrap .profile_img{
		width:128px;
		height:128px;
		border-radius:64px;
	}
	
	.profile_img_wrap .trash_cover{
		width:40px;
		height:40px;
		position:absolute;
		bottom:5px;
		z-index:1000;
		border:1px solid white;
		background-color:#aaaaaa99;
		border-radius:20px;
		padding:4px;
		box-sizing:border-box;
	}
	
	.profile_img_wrap .trash_cover > label{
		display: block;
		background-image:url('${path}/resources/img/trash.png');
		background-size:30px;
		background-repeat:no-repeat;
		width:30px;
		height:30px;
		margin:0;
	}
	
	.profile_img_wrap{
		position:relative;
		margin:20px auto;
		width:128px;
	}
	
	.profile_img_wrap .img_cover{
		width:40px;
		height:40px;
		position:absolute;
		bottom:5px;
		right:5px;
		z-index:1000;
		border-radius:20px;
		background-color:#aaaaaa99;
		border:1px solid white;
		box-sizing:border-box;
		padding:4px;
	}
	
	.profile_img_wrap .img_cover .img_file,
	#delete_img{
		display:none;
	}
	
	.fileDrop{
		width:100%;
		height:200px;
		background-color: #ccc;
		border : 1px solid black;
	}
</style>
</head>
<body>
	<h1>Upload Ajax</h1>
	
	<div class="profile_img_wrap">
		<img id="profile_img" class="profile_img" src="${path}/resources/img/profile.jpg"/>
			<div class="trash_cover" id="delete_img">
				<label></label>
			</div>
		<div class="img_cover">
			<label for="img_file"></label>
			<input type="file" class="img_file"
				   onchange="profileUpload(this.files);" 	 
				   id="img_file" accept=".gif, .jpg, .png"/>
			<input type="hidden" id="uimage" />
		</div>
	</div>
	
	<br/><hr/><br/>
	<h2>file drag &amp; drop</h2>
	<div class="fileDrop"></div>
	<div id="uploadedList">
	</div>
	
	<br/><hr/><br/>
	<form action="uploadForm" method="POST" enctype="multipart/form-data">
		<div>
			<img src="${path}/resources/img/profile.jpg" id="sampleImage" />
			<br/>
			<input type="file" name="file" id="profileImage" accept="image/*" />
			<input type="submit" />
		</div>
	</form>
	
<script src="http://code.jquery.com/jquery-latest.min.js"></script>
<script>
	$("#profileImage").on("change",function(){
		var files = this.files; // this == file(profileImage) | 여러개 전달 할 수 있기 때문에 files는 배열임 
		console.log(files);
		
		var file = files[0];
		console.log(file);
		
		// 사용자 컴퓨터에서 실제 파일 이 저장된 위치 정보를 문자열로 반환
		var path = window.URL.createObjectURL(file);
		console.log(path);
		
		$("#sampleImage").attr("src",path);
	});
	
	var profile = "";
	
	function profileUpload(files){
		console.log(files);
		// ajax로 폼데이터(입력폼) 형태로 전송을 가능하게 해주는 객체
		var formData = new FormData();
		formData.append("file",files[0]);
		
		$.ajax({
			type : "POST",
			url : "uploadAjax",
			data : formData,
			contentType : false,	// 인코딩타입 적용 안하겠다.
			processData : false,	// 쿼리스트링 생성 안하겠다.
			dataType : "text",		
			success : function(result){
				//alert(result);
				profile = $("#profile_img").attr("src");							
				$("#profile_img").attr("src","${path}/upload"+result);
				$("#uimage").val(result);
				$("#delete_img").fadeIn("fast");
			}
		});
	}
	// 파일 정보 삭제
	$("#delete_img").click(function(){
		var fileName = $("#uimage").val();
		
		$.ajax({
			url : "deleteFile",
			type : "DELETE",
			data : {fileName : fileName},
			dataType : "text",
			success : function(result){
				alert(result);
				$("#profile_img").attr("src",profile);
				$("#delete_img").fadeOut("fast");
			},
			error : function(res){
				alert(res.responseText);
			}
		});
	});
	
	// drag & drop
	$(".fileDrop").on("dragenter dragover",function(event){
		event.preventDefault();
	});
	
	$(".fileDrop").on("drop",function(event){
		event.preventDefault();
		// alert("drop!!!!")
		var files = event.originalEvent.dataTransfer.files;
		console.log("files");
		console.log(files);
		
		var formData = new FormData();
		
		console.log(files.length);
		for(var i=0; i<files.length; i++){
			var file = files[i];
			console.log(file);
			var maxSize = 10485760; // 10MB
			if(maxSize < file.size){
				alert("업로드 할 수 없는 크기의 파일입니다.");
				return;
			}
			formData.append("files",file);
		}
		
		$.ajax({
			type : "POST",
			url : "uploadFiles",
			data : formData, 
			dataType : "json",	// list로 전달받아야 되서 json으로 받는데
			processData : false,
			contentType : false,
			success : function(result){
				// List<String> saves == result
				console.log(result);
				var str = "";
				$(result).each(function(){
					console.log(this);
					str +="<div>";
					if(checkImageType(this)){
						console.log("이미지 파일");
						// downloadFile
						// str += "<a href='${path}/upload"+getOriginImage(this)+"' target='_blank'>";
						str += "<a href='downloadFile?fileName="+getOriginImage(this)+">";
						str +="<img src='${path}/upload"+this+"' />";
						str +="</a>";
					}else{
						// js에서는 null값이 거짓이라는 의미로도 쓰임
						console.log("일반 파일");
						// str += "<a href='${path}/upload"+this+"'>";
						str += "<a href='downloadFile?fileName="+this+"'>";
						str +="<img src='${path}/resources/img/file.png' />";
						str +=getOriginalName(this);
						str +="</a>";
					}
					str +="&nbsp;&nbsp;&nbsp;<span data-giguen='"+this+"'>&times;</span>";
					str +="</div>";
				}); // each end
				$("#uploadedList").append(str);
			}
		});
	});
	
	$("#uploadedList").on("click","span",function(){
		var target = $(this);
		var fileName = target.attr("data-giguen");
		console.log(fileName);
		$.ajax({
			type : "DELETE",
			url : "deleteFile",
			dataType : "text",
			data : {fileName : fileName},
			success : function(result){
				if(data = "DELETED"){
					alert("삭제 완료");
					target.parent("div").remove();
				}
			}
		});
	});
	
	// 원본 이미지 파일 이름
	function getOriginImage(fileName){
		return fileName.replace("s_","");
	}
	
	// 원본 파일 이름 출력
	function getOriginalName(fileName){
		var index = fileName.indexOf("_")+1;
		return fileName.substr(index);
	}
	
	function checkImageType(fileName){
		// 정규 표현식 - 특정 패턴의 문자열을 찾기 위한 표현방식
		// Regular expression - 규칙적인 패턴
		var pattern = /jpg|jpeg|gif|png/i;
		var result = fileName.match(pattern);
		console.log(result);
		return result;
	}
	
	
</script>
</body>
</html>