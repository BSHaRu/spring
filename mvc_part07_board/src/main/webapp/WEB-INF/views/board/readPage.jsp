<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>readPage.jsp</title>
</head>
<body>
	<h3>Read Page Board</h3>
	<!-- model boardVO -->
	<div>
		<label>Title</label>
		<input type="text" name="title" value="${boardVO.title }" readonly />
	</div>
	<div>
		<label>Content</label>
		<textarea name="content" rows=3>${boardVO.content }</textarea>
	</div>
	<div>
		<label>Writer</label>
		<input type="text" name="writer" value="${boardVO.writer }" readonly />
	</div>
	<div>
		<button id="modify">Modify</button>
		<button id="remove">Remove</button>
		<button id="list">List Page</button>
	</div>
	
	<!-- 나중에 어노테이션 설정 했을때랑 안했을때 차이 확인해봐래 -->
	criteria : ${criteria } <br/>
	cri : ${cri } <br/>
	
	<form id="pageForm" method="post">
		<input type="hidden" name="bno" value="${boardVO.bno }" />
		<input type="hidden" name="page" value="${cri.page }" />
		<input type="hidden" name="perPageNum" value="${cri.perPageNum }" />
	</form>
	<!-- button은 js가 있어야 동작하기때문에 jquery 추가해줌 -->
<script src="http://code.jquery.com/jquery-latest.min.js"></script>
<script>
	// 문서가 모두 로드 되었을 떄 실행 될 함수
	$(function(){
		var formObj = $("#pageForm");
		// list 버튼이 클릭 되었을 때
		$("#list").click(function(){
			$("input[name='bno']").attr("disabled","disabled"); // name이 bno일때 해당 값 전달 안하겠다.
			formObj.attr("action","listPage");
			formObj.attr("method","get");
			formObj.submit();
		});
		
		$('#modify').click(function(){
			formObj.attr("action","modifyPage");
			formObj.attr("method","get");
			formObj.submit();
		});
		
		$('#remove').click(function(){
			formObj.attr("action","removePage");
			formObj.submit();
		});
	});
</script>

</body>
</html>