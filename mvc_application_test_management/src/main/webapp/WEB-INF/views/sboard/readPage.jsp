<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="f" uri="http://java.sun.com/jsp/jstl/fmt"%>
<jsp:include page="../include/header.jsp" />
<section class="content">
	<div class="row">
		<div class="col-md-12">
			<div class="box">
				<div class="box-header with-border">
					<h3 class="box-title">
						SEARCH READ PAGE - ${board.bno}
					</h3>
				</div>
				<div class="box-body">
					<div class="form-group">
						<label>TITLE</label>
						<input type="text" name="title" 
							   class="form-control" placeholder="ENTER TITLE" readonly
							   value="${board.title}"/>
					</div>
					<div class="form-group">
						<label>CONTENT</label>
						<textarea name="content" class="form-control" rows="3"
						 placeholder="Enter Content" readonly>${board.content}</textarea>
					</div>
					<div class="form-group">
						<label>WRITER</label>
						<input type="text" name="writer" 
							   class="form-control" placeholder="ENTER WRITER" readonly
							   value="${board.writer}"/>
					</div>
					<div class="form-group">
						<label>REGDATE - </label>
						<f:formatDate pattern="yyyy-MM-dd HH:mm" value="${board.regdate}"/>
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						<label>UPDATEDATE - </label>
						<f:formatDate pattern="yyyy-MM-dd HH:mm" value="${board.updatedate}"/>
					</div>
				</div>
				<div class="box-footer">
					<input type="button" class="btn btn-warning" value="MODIFY" />
					<input type="button" class="btn btn-danger" value="DELETE" />
					<input type="button" class="btn btn-primary" value="LIST" />
				</div>
				<form id="readForm" method="post">
					<input type="hidden" name="bno" value="${board.bno}"/>
					<input type="hidden" name="page" value="${cri.page}"/>
					<input type="hidden" name="perPageNum" value="${cri.perPageNum}"/>
					<input type="hidden" name="searchType" value="${cri.searchType}"/>
					<input type="hidden" name="keyword" value="${cri.keyword}"/>
				</form>
			</div>
		</div>
	</div>
</section>
<script>
	$(function(){
		
		var formObj = $("#readForm");
		
		$(".btn-warning").click(function(){
			/* 수정  */
			formObj.attr("action","modifyPage");
			formObj.attr("method","get");
			formObj.submit();
		});
		
		$(".btn-danger").click(function(){
			/* 삭제  */	
			formObj.attr("action","removePage").submit();
		});	
			
		$(".btn-primary").click(function(){
			/* 리스트  */
			formObj.attr("action","listPage");
			formObj.attr("method","get");
			formObj.submit();
		});	
	});

</script>
<jsp:include page="../include/footer.jsp" />



















