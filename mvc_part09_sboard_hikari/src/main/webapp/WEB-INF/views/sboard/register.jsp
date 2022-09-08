<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<jsp:include page="../include/header.jsp"/>
<section class="content">
	<div class="row">
		<div class="col-md-12">
			<div class="box">
				<div class="box-header with-border">
					<h3 class="box-title">
						Search Register Page
					</h3>
				</div>
				<!-- action="sboard/register" method="post" -->
				<form method="post">
					<div class="box-body">
						<div class="form-group">
							<label>Title</label>
							<input type="text" class="form-control" name="title" placeholder="Enter Title" />
						</div>
						<div class="form-group">
							<label>Content</label>
							<textarea name="content" class="form-control" rows="3" placeholder="Enter Content"></textarea>
						</div>
						<div class="form-group">
							<label>Writer</label>
							<input type="text" class="form-control" name="writer" placeholder="Enter Writer" />
						</div>
					</div>
					
					<div class="box-footer">
						<input type="submit" value="Submit" class="btn btn-warning" />
					</div>
				</form>
			</div>
		</div>
	</div>
</section>
<jsp:include page="../include/footer.jsp"/>