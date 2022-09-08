<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="f" uri="http://java.sun.com/jsp/jstl/fmt"%>
<jsp:include page="../include/header.jsp"/>
<section class="content">
	<div class="row">
		<div class="col-md-12">
			<div class="box">
				<div class="box-header with-border">
					<h3 class="box-title">
						Search read Page - ${board.bno }
					</h3>
				</div>
				<div class="box-body">
					<div>
						<label>Title</label>
						<label>${board.title }</label>
					</div>
					<div>
						<label>Content</label>
						<label>${board.content }</label>
					</div>
					<div>
						<label>Writer</label>
						<label>${board.writer }</label>
					</div>
					<div>
						<label>RegDate - </label>
						<f:formatDate pattern="yyyy-MM-dd HH:mm" value="${board.regdate}" />
					</div>
				</div>

				<div class="box-footer">
					<button class="btn btn-warning">Modify</button>
					<button class="btn btn-danger">Delete</button>
					<button class="btn btn-primary">List</button>
				</div>
				<form id="readForm" method="get">
					<!-- 여기서 board는 Model에 board값으로 저장한걸 말하는거임 -->
					<input type="hidden" name="bno" value="${board.bno }" />
					<!-- 여기서 cri는 컨트롤러에서 지정한 @ModelAttribute("cri")의 cri를 의미함 -->
					<input type="hidden" name="page" value="${cri.page }" />
					<input type="hidden" name="perPageNum" value="${cri.perPageNum }" />
					<input type="hidden" name="searchType" value="${cri.searchType }" />
					<input type="hidden" name="keyword" value="${cri.keyword }" />
				</form>
			</div>
		</div>
	</div>
</section>

<script>
	var formObj = $("#readForm");
	// List버튼 클릭되었을 때
	$(".btn-primary").click(function(){
//		location.href='listPage';
		// attr : 속성값 변경 | action을 listPage로 변경하겠다
		formObj.attr("action","listPage").submit();
	});
	
	// 수정 버튼 클릭
	$(".btn-warning").click(function(){
		formObj.attr("action","modifyPage");
		formObj.submit();
	});
	
	// 삭제 버튼 클릭
	$(".btn-danger").click(function(){
		formObj.attr("action","removePage");
		formObj.attr("method","POST"); 
		formObj.submit();
	});
	
</script>




<jsp:include page="../include/footer.jsp"/>