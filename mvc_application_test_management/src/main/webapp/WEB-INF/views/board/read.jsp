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
						TEST READ PAGE - ${boardVO.bno}
					</h3>
				</div>
				<div class="box-body">
					<div class="form-group">
						<label>TITLE</label>
						<input type="text" name="title" 
							   class="form-control" placeholder="ENTER TITLE" readonly
							   value="${boardVO.title}"/>
					</div>
					<div class="form-group">
						<label>CONTENT</label>
						<textarea name="content" class="form-control" rows="3"
						 placeholder="Enter Content" readonly>${boardVO.content}</textarea>
					</div>
					<div class="form-group">
						<label>WRITER</label>
						<input type="text" name="writer" 
							   class="form-control" placeholder="ENTER WRITER" readonly
							   value="${boardVO.writer}"/>
					</div>
					<div class="form-group">
						<label>REGDATE - </label>
						<f:formatDate pattern="yyyy-MM-dd HH:mm" value="${boardVO.regdate}"/>
					</div>
				</div>
				<div class="box-footer">
					<input type="button" id="modify" class="btn btn-warning" value="MODIFY" />
					<input type="button" id="remove" class="btn btn-danger" value="DELETE" />
					<input type="button" id="list" class="btn btn-primary" value="LIST" />
				</div>
				<form role="form" method="post">
					<input type="hidden" name="bno" value="${boardVO.bno}"/>
					<input type="hidden" name="page" value="${cri.page}"/>
					<input type="hidden" name="perPageNum" value="${cri.perPageNum}"/>
				</form>
			</div>
		</div>
	</div>
</section>
<script src="http://code.jquery.com/jquery-latest.min.js"></script>
<script>
	$(function(){
		var formObj = $("form[role='form']");
		
		$("#modify").on("click",function(){
			formObj.attr("action","modify");
			formObj.attr("method","get");
			formObj.submit();
		});
		
		$("#remove").on("click",function(){
			formObj.attr("action","remove");
			formObj.submit();
		});
		
		$("#list").click(function(){
			formObj.attr("action","list");
			formObj.attr("method","get");
			formObj.submit();
		});
	});
</script>
<jsp:include page="../include/footer.jsp" />











