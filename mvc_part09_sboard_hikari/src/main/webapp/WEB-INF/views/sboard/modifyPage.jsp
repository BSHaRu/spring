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
						Search Modify Page
					</h3>
				</div>
				<!-- action="sboard/modifyPage" method="post" -->
				<form action="modifyPage" method="post">
					<div class="box-body">
						<div class="form-group">
							<label>Title</label>
							<input type="text" class="form-control" name="title" placeholder="Enter Title" value="${board.title }" />
						</div>
						<div class="form-group">
							<label>Content</label>
							<textarea name="content" class="form-control" rows="3" placeholder="Enter Content">${board.content }</textarea>
						</div>
						<div class="form-group">
							<label>Writer</label>
							<input type="text" class="form-control" name="writer" placeholder="Enter Writer"  value="${board.writer }" />
						</div>
					</div>
					
					<div class="box-footer">
						<input type="submit" value="Submit" class="btn btn-warning" />
					</div>
					
					<!-- 여기서 해당 값을 한번 더 불러 오기 때문에 form 태그에 action을 지정해줘야됨 -->
					<!-- action을 지정 안하면 동일한 값이 2번 들어가기 때문에 배열로 인식해서 2번 전달하게됨  
					 -> sboard/modifyPage?page=1&perPageNum=10&searchType=title&keyword=수정 인 상태에 
					 아래 값들을 전달 받는거라서 오류가 생김 -->
					<input type="hidden" name="bno" value="${board.bno }" />
					<input type="hidden" name="page" value="${cri.page }" />
					<input type="hidden" name="perPageNum" value="${cri.perPageNum }" />
					<input type="hidden" name="searchType" value="${cri.searchType }" />
					<input type="hidden" name="keyword" value="${cri.keyword }" />
					
				</form>
			</div>
		</div>
	</div>
</section>
<jsp:include page="../include/footer.jsp"/>