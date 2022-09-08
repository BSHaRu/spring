<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<jsp:include page="../include/header.jsp" />
<section class="content">
	<div class="row">
		<div class="col-md-12">
			<div class="box">
				<div class="box-header with-border">
					<h3 class="box-title">
						TEST MODIFY PAGE
					</h3>
				</div>
				<form action="modify" method="post">
					<div class="box-body">
						<input type="hidden" name="bno" value="${boardVO.bno}"/>
						<input type="hidden" name="page" value="${cri.page}"/>
						<input type="hidden" name="perPageNum" value="${cri.perPageNum}"/>
						<div class="form-group">
							<label>TITLE</label>
							<input type="text" name="title" 
								   class="form-control" placeholder="ENTER TITLE" 
								   value="${boardVO.title}"/>
						</div>
						<div class="form-group">
							<label>CONTENT</label>
							<textarea name="content" class="form-control" rows="3"
							 placeholder="Enter Content">${boardVO.content}</textarea>
						</div>
						<div class="form-group">
							<label>WRITER</label>
							<input type="text" name="writer" 
								   class="form-control" readonly
								   value="${boardVO.writer}"/>
						</div>
					</div>
					<div class="box-footer">
						<input type="submit" class="btn btn-primary" value="MODIFY"/>
					</div>
				</form>
			</div>
		</div>
	</div>
</section>
<jsp:include page="../include/footer.jsp" />